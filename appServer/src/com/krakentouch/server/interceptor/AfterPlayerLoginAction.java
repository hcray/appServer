package com.krakentouch.server.interceptor;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AfterPlayerLoginAction {
	@Pointcut("execution(* com.krakentouch.server.action.PlayerLoginAction.*(..))")  
    private void anyMethod(){}//定义一个切入点
	
	@AfterReturning("anyMethod()")  
	public void afterReturning() {
		System.out.println("登录后通知其他的用户");
		//TODO
		
	}


}
