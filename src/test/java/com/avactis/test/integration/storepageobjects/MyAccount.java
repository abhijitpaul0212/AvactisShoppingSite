package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utilities.WaitTool;

public class MyAccount extends LoadableComponent<MyAccount>
{
	@FindBy(xpath="//input[@name='order_id']")
	WebElement edt_OrderId;
	
	public MyAccount()
	{
		PageFactory.initElements(Browser.driver(), this);
		get();
	}

	@Override
	protected void load() 
	{
		
	}

	@Override
	protected void isLoaded() throws Error 
	{
	  WaitTool.waitForElementPresent(edt_OrderId, 5, 100);		
	}

}
