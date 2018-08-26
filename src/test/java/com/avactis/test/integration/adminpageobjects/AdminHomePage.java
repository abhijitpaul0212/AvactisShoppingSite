package com.avactis.test.integration.adminpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.Screenshot;
import com.avactis.test.integration.utilities.WaitTool;

public class AdminHomePage extends LoadableComponent<AdminHomePage>
{
	@FindBy(xpath="//li[@id='menu-orders']//a[@href='orders.php']")
	WebElement link_orders;
	
	@FindBy(name="order_id")
	WebElement edt_orderId;
	
	@FindBy(xpath="//a[contains(@onclick,'SearchById')]")
	WebElement btn_search;
	
	@FindBy(xpath="//a[@title='Order Info']")
	WebElement label_orderInfo;
	
	@FindBy(xpath="//span[contains(text(),'Admin Name')]")
	WebElement link_adminName;
	
	@FindBy(xpath="//span[contains(text(),'Admin Name')]/ancestor::li//a[@title='Sign Out']")
	WebElement link_signOut;
	
	public AdminHomePage()
	{
		PageFactory.initElements(Browser.driver(), this);
		get();
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(Browser.driver().getTitle().equals("Home - Avactis Shopping Cart"));
		
	}
	
	public String searchOrder(String orderId)
	{
		if(WaitTool.waitForElementPresent(link_orders, 80, 200))
		{
			link_orders.click();
			edt_orderId.sendKeys(orderId);
			btn_search.click();
			String searchedOrderId = label_orderInfo.getText();
			Log4j.info("Order Id found in Admin: "+searchedOrderId);
			return searchedOrderId;
			//Assert.assertEquals(Browser.driver().findElement(By.xpath("//a[@title='Order Info']")).getText(), "23422");
		}
		else
		{
			Screenshot.takeScreenshot("Order link not found");
			return null;
		}
	}
	
	public void adminSignOut()
	{
		Actions builder = new Actions(Browser.driver());
		builder.moveToElement(link_adminName).click(link_signOut).build().perform();
		WaitTool.waitForElementInvisibility(link_signOut, 3, 100);
		Log4j.info("Admin user sucessfully logged out");
	}

}
