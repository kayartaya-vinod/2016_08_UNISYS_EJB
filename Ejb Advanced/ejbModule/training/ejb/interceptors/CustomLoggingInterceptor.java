package training.ejb.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class CustomLoggingInterceptor {

	@AroundInvoke
	public Object aroundMailSchedulerMethods(InvocationContext ctx) throws Exception{
		System.out.println("Inside the CustomLoggingInterceptor, before going to " +
				ctx.getMethod().getName());
		Object obj = ctx.proceed();
		System.out.println("Inside the CustomLoggingInterceptor, after coming from " +
				ctx.getMethod().getName());
		return obj;
	}
}
