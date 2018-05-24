package training.ejb.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;

import training.ejb.MailScheduler;
import training.ejb.interceptors.CustomLoggingInterceptor;

// timer service is not available in stateful beans
@Stateless
@Interceptors({ CustomLoggingInterceptor.class })
public class MailSchedulerBean implements MailScheduler {

	@Resource
	SessionContext ctx;

	TimerService ts;

	@PostConstruct
	public void init() {
		ts = ctx.getTimerService();
	}

	@Override
	public void sendMailOn(Date date) {
		System.out.println("Inside the sendMailOnMethod() start");
		// do not send mail here; schedule one.
		ts.createTimer(date, "cron1");
		System.out.println("Inside the sendMailOnMethod() end");
	}

	@Override
	public void sendMailAfter(long duration) {
		// do not send mail here; schedule one.
		ts.createTimer(duration, "cron2");
	}

	@Timeout
	public void cronJob(Timer timer) {
		String id = (String) timer.getInfo();
		if (id.equals("cron1")) {
			System.out.println(">>>>>>>> Sending a mail on a particular date/time");
		} else if (id.equals("cron2")) {
			System.out.println(">>>>>>>> Sending a mail after a gap of 'n' millis");
		}
	}

}
