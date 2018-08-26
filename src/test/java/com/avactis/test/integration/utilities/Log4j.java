package com.avactis.test.integration.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4j
{	
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	
	//Wrapper methods
	public static void info(String message) 
	{
		DOMConfigurator.configure("log4j.xml");		 
		Log.info(message);		 
	}
		 
	public static void warn(String message) 
	{
		DOMConfigurator.configure("log4j.xml");
	    Log.warn(message);
	
		}
		 
	public static void error(String message) 
	{
		DOMConfigurator.configure("log4j.xml");		 
	    Log.error(message);
	 
	}
	 
	public static void fatal(String message) 
	{
		DOMConfigurator.configure("log4j.xml");		 
	    Log.fatal(message);
	 
	}
		 
	public static void debug(String message) 
	{
		DOMConfigurator.configure("log4j.xml");		 
	    Log.debug(message);
	}
}
