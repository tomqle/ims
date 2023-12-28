package dev.tomle.ims.application.shared;

import org.slf4j.Logger;

public final class LogUtil {
	public static void enterMethod(Logger logger, String className, String methodName, Object... args) {
		String logMessage = "Entering method " + className + "." + methodName + " args (";
		for(int i = 0; i < args.length; i++) {
			if(i != 0) {
				logMessage += ", ";
			}
			logMessage += args;
		}
		logMessage += ")";
		logger.debug(logMessage);
	}
	
	public static void exitMethod(Logger logger, String className, String methodName, Object ret) {
		String logMessage = "Exiting method " + className + "." + methodName + " return " + ret;
		logger.debug(logMessage);
	}

	public static void exitMethod(Logger logger, String className, String methodName) {
		String logMessage = "Exiting method " + className + "." + methodName + " void";
		logger.debug(logMessage);
	}
}
