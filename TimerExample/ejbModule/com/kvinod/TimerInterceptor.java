package com.kvinod;

import java.util.Arrays;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TimerInterceptor {

	@AroundInvoke
	public Object fun4(InvocationContext ctx) throws Exception {
		System.out.println(">>>> going to invoke " + ctx.getMethod().getName());
		System.out.println(">>>> arguments are: " + Arrays.toString(ctx.getParameters()));
		System.out.println(">>>> target object is: " + ctx.getTarget());
		if(ctx.getMethod().getName().equals("createTimer")){
			System.out.println("Changing the argument to -5000 ms");
			ctx.setParameters(new Object[]{5000L});
		}
		Object obj = ctx.proceed();
		System.out.println("<<<< just finished executing " + ctx.getMethod().getName());
		return obj;
	}
}
