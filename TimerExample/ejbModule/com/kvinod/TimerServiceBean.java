package com.kvinod;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.interceptor.AroundTimeout;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Interceptors({ TimerInterceptor.class })
@Stateless
public class TimerServiceBean implements TimerService {

	@Resource
	private SessionContext context;

	@Override
	public void createTimer(long duration) {
		System.out.println(">>>>> Creating a timer for " + duration + "ms, at " + new Date());

		context.getTimerService().createTimer(duration, "Hello, world!");
	}

	// only one timeout is allowed per stateless bean
	// timeout is not allowed for stateful beans
	@Timeout
	public void doAfterTimeout(Timer timer) {
		System.out.println("Timer '" + timer.getInfo() + "' finished at " + new Date());
		timer.cancel();
	}

	@Override
	public void executeOn(Date dt) {
		context.getTimerService().createTimer(dt, "timer2");
	}

	@PostConstruct
	public void pc() {
		System.out.println(">>>>> This is @PostConstruct for TimerServiceBean...");
	}

	@PreDestroy
	public void pd() {
		System.out.println(">>>>> This is @PreDestroy for TimerServiceBean...");
	}

	@AroundTimeout
	public Object at(InvocationContext ctx) throws Exception {
		System.out.println(
				">>>>> This is @AroundTimeout for TimerServiceBean, before invoking " + ctx.getMethod().getName());
		Object ob = ctx.proceed();
		System.out.println(
				">>>>> This is @AroundTimeout for TimerServiceBean, after invoking " + ctx.getMethod().getName());
		return ob;
	}

}
