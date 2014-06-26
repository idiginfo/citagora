package org.idiginfo.docsvc.jpa.citagora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoadAppContext {
	private static ApplicationContext context = null;
	
	public static ApplicationContext getContext() {
		if(context == null)
		{
			context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		}
		return context;
	}
	public static ApplicationContext getContext(String contextFile) {
		return new ClassPathXmlApplicationContext(contextFile);
	}
}
