package org.idiginfo.docsvc.jpa.citagora;

import java.beans.Introspector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

//@Service
public class CitagoraApplicationContext {
	public ApplicationContext context = null;
//	public static EntityManagerContainer emc = null;
	public CitagoraApplicationContext() {
		if(context == null)
		{
			context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		}
//		return context;
	}
	public  ApplicationContext getContext(String contextFile) {
		return new ClassPathXmlApplicationContext(contextFile);
	}


	public Object getBean(Class o){
		return context.getBean(Introspector.decapitalize(o.getSimpleName()));
	}
}
