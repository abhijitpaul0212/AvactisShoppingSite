package com.avactis.test.integration.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utilities.Log4j;

public class TestListener implements WebDriverEventListener, ITestListener 
{
	//Methods of WebDriverEventListener class

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside afterClickOn"+driver.);
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside afterFindBy");
		
	}

	public void afterNavigateBack(WebDriver driver) {
		//System.out.println(driver.getCurrentUrl());
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
	//	System.out.println("Inside afterNavigateForward");
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		Log4j.info("Inside afterNavigateTo - " + driver.getTitle());
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside afterScript");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		//element.clear();
		//System.out.println("Inside beforeChangeValueOf");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside beforeClickOn");
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside beforeFindBy");
		//System.out.println(element.getText());
	}

	public void beforeNavigateBack(WebDriver driver) {
		//System.out.println(driver.getCurrentUrl());
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside beforeNavigateForward");
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		Log4j.info("Inside beforeNavigateTo - " + driver.getTitle());
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println("Inside beforeScript");
	}

	public void onException(Throwable throwable, WebDriver driver) 
	{
		/*try 
		{
			//Log4j.error("onException triggered at Page Title: "+driver.getTitle());
			//FileUtils.cleanDirectory(new File("target/screenshots"));
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("target/screenshots/error"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log4j.fatal("Exception occured at " + e.getMessage());
		}*/
		
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}


	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}	
	
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	//Methods of ITestListener 
	
	public void OnStart(ITestContext Result) {
			
		
	}
	public void onTestSuccess(ITestResult Result) {
		Log4j.info("The name of the testcase passed is :"+Result.getName());	
		
		
	}
	public void onTestFailure(ITestResult Result) {
		Log4j.error("The name of the testcase failed is :"+Result.getName());	
		saveScreenshotPNG(Browser.driver());
		
	}
	public void onTestSkipped(ITestResult Result) {
		Log4j.warn("The name of the testcase skipped is :"+Result.getName());
		saveScreenshotPNG(Browser.driver());
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		// TODO Auto-generated method stub
		
	}
	public void onFinish(ITestContext Result) {
		//System.out.println(Result.getName()+" test case finished");
		
	}
	
	public void onTestStart(ITestResult result) {
		//System.out.println((result.getName()+" test case started"));
	}

	public void onStart(ITestContext context) {
		//System.out.println((context.getName()+" test case started"));
		
	}


	
	
	public byte[] saveScreenshotPNG(WebDriver driver)
	{
		System.out.println("Save screenshot called");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	
	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}


}
