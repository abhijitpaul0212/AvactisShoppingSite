package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.WaitTool;

import io.qameta.allure.Step;

public class SignInPage extends LoadableComponent<SignInPage>
{
	@FindBy(xpath="//input[contains(@id,'email_id')]")
	WebElement edt_emailID;
	
	@FindBy(xpath="//input[contains(@id,'passwd_id')]")
	WebElement edt_password;
	
	@FindBy(xpath="//input[contains(@value,'Sign In')]")
	WebElement btn_signIn;
	
	@FindBy(xpath="//button[contains(text(),'Register')]")
	WebElement btn_register;
	
	public SignInPage() 
	{
		PageFactory.initElements(Browser.driver(), this);
		get();
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error 
	{		
		WaitTool.waitForElementPresent(btn_register, 5, 100);
	}
	
	@Step("[Step 3] Login to avactis site using Username: {0} and Password: {1}")
	public boolean doSignIn(String emailId, String password)
	{
		if(WaitTool.waitForElementPresent(edt_emailID, 30, 100))
		{
			edt_emailID.sendKeys(emailId);
			edt_password.sendKeys(password);
			btn_signIn.click();
			WaitTool.waitForElementInvisibility(btn_signIn, 10, 100);
			return true;
		}
		else
		{
			Log4j.error("Store signin unsuccessfull");
			return false;
		}
		
	}
}
