package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.avactis.test.integration.utilities.ConfigProperties;
import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.Screenshot;
import com.avactis.test.integration.utilities.WaitTool;

import io.qameta.allure.Step;

public class HomePage extends LoadableComponent<HomePage>
{
	String url;
	
	@FindBy(xpath="//a[contains(text(),'Sign In')]")
	WebElement link_signIn;
	
	@FindBy(xpath="//a[contains(text(),'My Account')]")
	WebElement link_mYAccount;
	
	@FindBy(xpath="//a[contains(text(),'My cart')]")
	WebElement link_myCart;
	
	@FindBy(xpath="//a[contains(@href,'sign-in.php?')]")
	WebElement link_signOut;
	
	public HomePage()
	{
		PageFactory.initElements(Browser.driver(), this);
		ConfigProperties.loadProperties();
		url = ConfigProperties.getProperty("STORE_URL");
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		Log4j.info(Browser.driver().getTitle());
		Assert.assertTrue(Browser.driver().getTitle().equals("Avactis Demo Store"));	
	}

	@Override
	protected void load() 
	{
		Browser.open(url);
	}
	
	@Step("To verify is user is already logged in")
	public boolean isUserLoggedIn()
	{
		if (WaitTool.waitForElementPresent(link_signOut, 2, 100))
		{			
			return true;
		}
		Screenshot.takeScreenshot("signOut_link_notFound");
		return false;			
	}
	
	@Step("Go to SignIn page")
	public SignInPage goToSignInPage()
	{
		if(!WaitTool.waitForElementPresent(link_signIn, 30, 200))
		{
			Screenshot.takeScreenshot("signIn_link_notFound");
		}
		else
		{
			Log4j.info("About to click on signIn");
			link_signIn.click();	
			return new SignInPage();
		}
		return null;
	}
	
	@Step("Signout if user is already logged in")
	public SignInPage doSignOut()
	{
		if(isUserLoggedIn())
		{
			link_signOut.click();
			return new SignInPage();
		}
		else
		{
			Log4j.warn("No user is presently logged in");
			return null;
		}
	}
	
	@Step("Navigate to Menu Item > SubMenu Item")
	public ProductPage goToProductPageUsingMenuAndSubMenu(String mainMenuID, String subMenuID)
	{
		Actions builder = new Actions(Browser.driver());
		
		String xpathMainMenu = "//a[contains(@href,'"+mainMenuID+"')]";
		String xpathSubMenu = "//a[contains(@href,'"+subMenuID+"')]";
		
		WaitTool.waitForElementPresent(Browser.driver().findElement(By.xpath(xpathMainMenu)),100, 500);
		WebElement mainMenu = Browser.driver().findElement(By.xpath(xpathMainMenu));		
		builder.moveToElement(mainMenu).build().perform();
		
		WebElement subMenu = Browser.driver().findElement(By.xpath(xpathSubMenu));
		String subMenuText =subMenu.getText();
		builder.moveToElement(subMenu).build().perform();
		subMenu.click();
		return new ProductPage(subMenuText);		
	}
	
}
