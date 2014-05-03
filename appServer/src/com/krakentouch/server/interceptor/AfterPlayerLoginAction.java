package com.krakentouch.server.interceptor;

import java.util.Collection;

import org.apache.mina.core.session.IoSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AfterPlayerLoginAction {
	@Pointcut("execution(* com.krakentouch.server.action.PlayerLoginAction.*(..))")  
    private void anyMethod(){}//定义一个切入点
	
	@AfterReturning("anyMethod()")  
	public void afterReturning(JoinPoint point) {
		Object[] args = point.getArgs();
		IoSession session = (IoSession) args[0];
		System.out.println("登录后通知其他的用户");
		//TODO
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			s.write("hello i am online");
		}
	}


}
