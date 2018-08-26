package com.avactis.test.integration.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

public class ConfigProperties 
{
private static final String PROPERTIES_FILE_PATH = "config\\avactis.properties";
private static Properties prop = new Properties();
	
	public static void loadProperties()
	{
		try
		{
			prop.load(new FileInputStream(PROPERTIES_FILE_PATH));
			
		}catch (FileNotFoundException e)
		{
			System.out.println("Config Properties file not found");
		} catch (IOException e)
		{
			System.out.println("IO exception while accessing confif.properties file");
		}
		Assert.assertTrue(!prop.isEmpty());
	}
	
	public static String getProperty(String key)
	{
		Log4j.info("Key: "+key+" : Value: "+prop.getProperty(key));
		return prop.getProperty(key);
	}
	
	public static void setProperty(String key, String keyValue)
	{
		prop.setProperty(key, keyValue);
		File configFile = new File("config\\avactis.properties");
		FileWriter writer = null;
		try 
		{
			writer = new FileWriter(configFile);
			prop.store(writer, "Avactis properties file");
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
			e1.getMessage();
		}
	}
}
