package com.avactis.test.integration.adminpageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utilities.ConfigProperties;
import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.Screenshot;
import com.avactis.test.integration.utilities.WaitTool;

public class AdminSignInPage extends LoadableComponent<AdminSignInPage>
{
	private String url;
	@FindBy(name="AdminEmail")
	WebElement edt_userName;
	
	@FindBy(name="Password")
	WebElement edt_password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn_signIn;
	
	
	public AdminSignInPage()
	{
		PageFactory.initElements(Browser.driver(), this);
		ConfigProperties.loadProperties();
		url = ConfigProperties.getProperty("ADMIN_URL");
		//get();
	}


	@Override
	protected void load() 
	{
		Browser.open(url);
	}


	@Override
	protected void isLoaded() throws Error 
	{
		Assert.assertTrue(Browser.driver().getTitle().equals("Avactis Shopping Cart"));
		Log4j.debug("Admin page is loaded");
		
	}
	
	public AdminHomePage adminSignIn(String username, String password)
	{
		AdminHomePage adminHomePage = null;
		if(WaitTool.waitForElementPresent(edt_userName, 30, 200))
		{
			edt_userName.sendKeys(username);
			edt_password.sendKeys(password);
			btn_signIn.click();
			adminHomePage = new AdminHomePage();
			return adminHomePage;
		}
		else
		{
			Screenshot.takeScreenshot("Admin Signin not found");
			return null;
		}
		
		
	}
	
	
	

}
