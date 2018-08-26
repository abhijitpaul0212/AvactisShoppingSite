package com.avactis.test.integration.storepageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.Screenshot;
import com.avactis.test.integration.utilities.WaitTool;

import io.qameta.allure.Step;

public class ProductPage extends LoadableComponent<ProductPage>
{
	@FindBy (xpath = "//a[@href='cart.php']")
	WebElement link_myCart;
	
	private String title = "Avactis Demo Store";
	private String selectedMenuString;
	public ProductPage(String selectedMenuString) 
	{
		this.selectedMenuString =selectedMenuString;
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
		Log4j.info("Page Title: "+selectedMenuString+" - "+title);
		Assert.assertTrue(Browser.driver().getTitle().equals(selectedMenuString+" - "+title));
	}
	
	@Step("Placing order once all items are added to cart")
	public void placeOrder(ProductPage productList, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
	{
		
		if (productList.addToCartButton(productID))
			Log4j.info("Add To Cart Is Successful");
		else 
			{
			Log4j.error("Add To Cart Is Not Successful");
			//throw new NoSuchElementException("Add To Cart Is Not Successful");
			}	
		
		if (!moreFlag.equals("Y"))
		{
			Log4j.info("More Flag = 'N' found");
	    	WaitTool.waitForElementPresent(link_myCart, 30, 100);
	    	link_myCart.click();		
			CheckOutPage checkOut = new CheckOutPage();	
			ArrayList<List<String>> expCartItemDetails = checkOut.checkOut_shoppingCart();
			checkOut.checkOut_billingAndShippingAddress();
			checkOut.checkOut_billingAndShippingMethod(paymentMethod,shipmentMethod);
			checkOut.placeOrderAndRetrieveOrderId(expCartItemDetails);
	    	}
		else
		{
			Log4j.warn("More Flag = 'Y' found, Looking out for Flag = N");
		}
		//return new HomePage();
	}
	
	@Step("Adding to cart")
	public boolean addToCartButton(String productID)
	{
		String AddtoCartButtonForGivenProduct = "ProductForm_" + productID;
		System.out.println("Searching for product ID: "+productID);
		
		WaitTool.waitForElementPresent(Browser.driver().findElement(By.id(AddtoCartButtonForGivenProduct)), 100,100);
		WebElement AddToCart;
		try
		{
			AddToCart = Browser.driver().findElement(By.xpath("//*[@id='"+AddtoCartButtonForGivenProduct+"']/descendant::input[@value='Add To Cart']"));
			//AddToCart = Browser.driver().findElement(By.xpath("//a[contains(@href,'"+productID+"')]/following-sibling::div/input[@value='Add To Cart']"));
			AddToCart.click();
			Log4j.info(productID+" product got added to cart");
		}
		catch (NoSuchElementException e) 
		{
			Log4j.error("Add to Cart button not found");
			Screenshot.takeScreenshot("addToCart_link_notFound");
			return false;
		}
		/*wait = new WebDriverWait(Browser.getDriver(),30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ajax_message_box_text")));		*/
		return true;
	}
}
