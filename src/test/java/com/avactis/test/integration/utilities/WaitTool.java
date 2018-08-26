package com.avactis.test.integration.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.storepageobjects.Browser;

public class WaitTool 
{

public static boolean waitForElementPresent(WebElement element, int timeOutInSeconds, int pollingTime) {
		
		try{
			//Browser.driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
			WebDriverWait wait = new WebDriverWait(Browser.driver(), timeOutInSeconds, pollingTime); 
			element = wait.until(ExpectedConditions.visibilityOf(element));			
			//Browser.driver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return true; //return the element
		} catch (Exception e) {
			e.getMessage();
			return false;
		} 
//		return null; 
	}

public static boolean waitForElementInvisibility(WebElement element, int timeOutInSeconds, int pollingTime) {
	
	try{
		//Browser.driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
		WebDriverWait wait = new WebDriverWait(Browser.driver(), timeOutInSeconds, pollingTime); 
		boolean val= wait.until(ExpectedConditions.invisibilityOf(element));			
		//Browser.driver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
		return true; //return the element
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} 
//	return null; 
}
}
