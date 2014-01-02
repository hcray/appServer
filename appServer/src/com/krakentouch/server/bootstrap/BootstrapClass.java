package com.krakentouch.server.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.krakentouch.server.domain.User;
import com.krakentouch.server.service.UserService;

public class BootstrapClass {

	public static void main(String[] args) {
		/*
		try {
			NioSocketAcceptor acceptor = new NioSocketAcceptor();
			
			ServerHandler serverHandler = new ServerHandler();
			
			acceptor.setHandler(serverHandler);
			
			// The logger, if needed. Commented atm
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			
			chain.addLast("logger", new LoggingFilter());
			
			chain.addLast("codec", new ServerCodec());
			
			SocketSessionConfig scfg = acceptor.getSessionConfig();
			
			acceptor.bind(new InetSocketAddress(18567));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
		ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("Server started...");
		
		UserService userService = (UserService) ct.getBean("userService");
		User user = new User();
		user.setName("tom");
		user.setAge(12);
		userService.insertUser(user);
	}

}
