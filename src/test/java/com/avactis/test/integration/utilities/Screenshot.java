package com.avactis.test.integration.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.avactis.test.integration.storepageobjects.Browser;

public class Screenshot 
{
	public static void takeScreenshot(String fileName)
	{
	try 
	{
		//Log4j.error("onException triggered at Page Title: "+driver.getTitle());
		//FileUtils.cleanDirectory(new File("target/screenshots"));
		String pathname = "target//screenshots//"+fileName+"_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".png";
		File srcFile = ((TakesScreenshot)Browser.driver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(pathname));
		Log4j.info("Screnshot taken: "+pathname);
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
		Log4j.fatal("Exception occured at " + e.getMessage());
	}
	}
}
