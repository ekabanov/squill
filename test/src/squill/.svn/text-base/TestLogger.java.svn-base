package squill;

import squill.mgen.MessageLogger;

/**
 * Implementation of MessageLogger for JUnit tests
 * 
 * @author Jevgeni Martjushev
 * 
 */
public class TestLogger implements MessageLogger {
	
	@Override
	public void error(String message, Throwable t) {
		System.out.println("ERROR: " + message + "EXCEPTION: " + t);
	}
	
	@Override
	public void info(String message) {
		System.out.println("INFO: " + message);
	}
	
	@Override
	public void warning(String message) {
		System.out.println("WARNING: " + message);
	}
	
}
