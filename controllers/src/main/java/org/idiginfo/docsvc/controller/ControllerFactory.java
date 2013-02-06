package org.idiginfo.docsvc.controller;

import java.util.logging.Logger;

import org.idiginfo.docsvc.model.citagora.CitagoraFactory;

public abstract class ControllerFactory {
    protected static ControllerFactory factory = null;

    public static ControllerFactory getFactory() {
	return factory;
    }

	// logging objects
	private static final String SYSTEM_LOGGER_NAME = "ServicesLogger";
	public static final Logger SYSTEM_LOGGER = Logger.getLogger(SYSTEM_LOGGER_NAME);
	public static String SYSTEM_LOG_FILE_NAME = "/data/logs/tomcat/service.log";

	public static void init(String persistence, String logFile) {
	    // TODO Auto-generated method stub

	   CitagoraFactory.setPersistence(persistence);
	}

}
