package com.avactis.test.integration.storepageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.TestListener;

public class Browser 
{
	private static WebDriver driver1;
	private static EventFiringWebDriver driver;
	private static TestListener li;
	
    /*public static WebDriver driver()
	{
		return driver1;
	}*/
	
	public static EventFiringWebDriver driver()
	{
		return driver;
	}
	
	 
	public static void setBrowser(String browsername)
	{
		try
		{
			
		if(browsername.toUpperCase().equals("CH"))
		{
			Log4j.info("Test will run on: "+browsername );
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
			driver1 = new ChromeDriver();
			driver = new EventFiringWebDriver(driver1);
			li = new TestListener();
			driver.register(li);
		}
		else
		{
			Log4j.info("Test will run on default broswer: Firefox");
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver64bit.exe");
			driver1 = new FirefoxDriver();
			driver = new EventFiringWebDriver(driver1);
			li = new TestListener();
			driver.register(li);
		}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void open(String url)
	{
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public static void close()
	{
		driver.quit();
		driver.unregister(li);
		try {
			Runtime.getRuntime().exec(new String[] {"cmd", "/K", "TASKKILL /IM chromedriver.exe /F"});
			Runtime.getRuntime().exec(new String[] {"cmd", "/K", "TASKKILL /IM chrome.exe /F"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
