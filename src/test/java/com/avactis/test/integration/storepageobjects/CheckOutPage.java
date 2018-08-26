package com.avactis.test.integration.storepageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utilities.ConfigProperties;
import com.avactis.test.integration.utilities.Log4j;
import com.avactis.test.integration.utilities.WaitTool;

import io.qameta.allure.Step;

public class CheckOutPage extends LoadableComponent<CheckOutPage>
{
	//private ArrayList<List<String>> myCartItemDetails = new ArrayList<List<String>>();
	//private ArrayList<List<String>> orderPreviewItemDetails = new ArrayList<List<String>>(); 
	@FindBy (xpath = "//a[text()='Continue Shopping']/following-sibling::a[1]")
	WebElement link_shoppingCart_CheckOut;
	
	@FindBy (xpath = "//*[@name='billingInfo[Firstname]']")
	WebElement edtbox_firstNameTextBox;
	
	@FindBy (xpath = "//*[@value='Continue Checkout'][contains(@onclick,'1')]")
	WebElement link_billingAndShippingAddress_CheckOut;
	
	@FindBy (xpath = "//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")
	WebElement link_billingAndShippingMethod_CheckOut;
	
	@FindBy (xpath = "//*[@value='Place Order']")
	WebElement link_placeOrder;
	
	@FindBy (xpath = "//h1[text()='Thank you for your order!']")
	WebElement label_OrderConfirmation;
	
	@FindBy (xpath = "//label[contains(text(),'Order Id')]/following-sibling::div")
	WebElement label_orderID;
	
	public CheckOutPage() 
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
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<List<String>> checkOut_shoppingCart()
	{
		ArrayList<List<String>> myCartItemDetails = new ArrayList<List<String>>();
		WaitTool.waitForElementPresent(link_shoppingCart_CheckOut, 20, 100);
		List<WebElement> tableElement = new ArrayList<WebElement>();
		tableElement=Browser.driver().findElements(By.xpath("//tr"));
		//System.out.println("Checkout table"+tableElement.size());
		 for(int j = 2; j <= tableElement.size(); j++)
		  {
			  String item = Browser.driver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[2]")).getText();
			  String qty = Browser.driver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[3]//option[@selected='selected']")).getText();
			  String price = Browser.driver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[4]")).getText();
			  String total = Browser.driver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[5]")).getText();
			  myCartItemDetails.add(Arrays.asList(item,qty,price,total));			  
		  }
		  
		  for(int j = 0; j < myCartItemDetails.size(); j++)
		  {				  
			  Log4j.info("Expected cart details: "+myCartItemDetails.get(j));
			  
		  }		
		link_shoppingCart_CheckOut.click();	
		return myCartItemDetails;

	}
	
	@Step("Entering all required fields in BillingAndShippingAddress")
	public void checkOut_billingAndShippingAddress()
	{
		WaitTool.waitForElementPresent(link_billingAndShippingAddress_CheckOut, 20, 100);
		//System.out.println(firstNameTextBox.getAttribute("value"));
		//System.out.println(firstNameTextBox.getAttribute("value").isEmpty());
		if(!edtbox_firstNameTextBox.getAttribute("value").isEmpty())
			link_billingAndShippingAddress_CheckOut.click();
		else
			System.out.println("Billing & Shipping address incomplete/invalid");
	}
	
	@Step("Entering all required fields in BillingAndShippingMethod")
	public void checkOut_billingAndShippingMethod(String paymentMethod,String shipmentMethod)
	{
		WaitTool.waitForElementPresent(link_billingAndShippingMethod_CheckOut, 50, 100);
		//String val = "Ground Shipping";
		//String shippingOptionXpath = "//label[text()=' "+shipmentMethod+"']/child::input[@type='radio']";
		//WebElement shippingOption = Browser.driver().findElement(By.xpath(shippingOptionXpath));
		
		List<WebElement> shippingMethods = Browser.driver().findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
		 for(WebElement shippingMethod:shippingMethods)
		  {
			  if(shippingMethod.getText().equalsIgnoreCase(shipmentMethod))
			  {
				  System.out.println(shippingMethod);
				  shippingMethod.click();
				  Log4j.info("Shipping method '"+shipmentMethod+"' is selected");
			  }
		  
		  }
		 
		/*	
		shippingOption.click();		
		Log4j.info("Shipping Method: "+shipmentMethod);*/
		link_billingAndShippingMethod_CheckOut.click();		
	}
	
	@Step("Verifying items prior placing order and placing the Order")
	public void placeOrderAndRetrieveOrderId(ArrayList<List<String>> expCartItemDetails)
	{
		ArrayList<List<String>> orderPreviewItemDetails = new ArrayList<List<String>>();
		WaitTool.waitForElementPresent(link_placeOrder, 80, 100);
		//link_placeOrder.click();
		
		List<WebElement> tableElement1 = new ArrayList<WebElement>();
		  tableElement1 = Browser.driver().findElements(By.xpath("//tr"));
		  
		  for(int j = 2; j <= tableElement1.size(); j++)
		  {
			  String item = Browser.driver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[1]")).getText();
			  String qty = Browser.driver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[2]")).getText();
			  String price = Browser.driver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[3]")).getText();
			  String total = Browser.driver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[4]")).getText();
			  orderPreviewItemDetails.add(Arrays.asList(item,qty,price,total));
		  }
		  for(int j = 0; j < orderPreviewItemDetails.size(); j++)
		  {				  
			  Log4j.info("Actual cart details: "+orderPreviewItemDetails.get(j));
			  
		  }	  
		  
		  //Comparision between expected cart list and actual cart list
		  if(expCartItemDetails.equals(orderPreviewItemDetails))
		  {
	        	Log4j.info("Order got placed succesfully as comparison result is matched");
	        	if(WaitTool.waitForElementPresent(link_placeOrder, 70, 100))
	        	{
	        		link_placeOrder.click();
	        		Log4j.info("Place order button is clicked");
	        		String orderId = fetchOrderID();
	        		if(orderId != null)
	        		{
	        			ConfigProperties.setProperty("ORDER_ID", orderId);
	        		}
	        	}
	        	else
	        	{
	        		Log4j.error("Place order button is not interactable");
	        	}
	        	
	       }
	       else
	       {
	    	   Log4j.error("Order placing got failed due to comparison result didnt matched");
	    	   throw new AssertionError("Order placing got failed due to comparison result didnt matched");
	    	   //throw new RuntimeException("Order placing got failed due to comparison result didnt matched");
	       }
	}
	
	@Step("Fetching Order ID after successfully placing the order")
	public String fetchOrderID()
	{
		if(WaitTool.waitForElementPresent(label_orderID, 70, 100))
		{
			String[] orderId = label_orderID.getText().split("#");
			Log4j.info("Order is placed successfully: "+orderId[1]);
			//ConfigProperties.setProperty("ORDER_ID", orderId[1]);
			return orderId[1];
		}
		else
		{
			return null;
		}
	}
	
	
	

}
