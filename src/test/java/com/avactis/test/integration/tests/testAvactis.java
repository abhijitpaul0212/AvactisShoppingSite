package com.avactis.test.integration.tests;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avactis.test.integration.adminpageobjects.AdminHomePage;
import com.avactis.test.integration.adminpageobjects.AdminSignInPage;
import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.storepageobjects.HomePage;
import com.avactis.test.integration.storepageobjects.ProductPage;
import com.avactis.test.integration.storepageobjects.SignInPage;
import com.avactis.test.integration.utilities.ConfigProperties;
import com.avactis.test.integration.utilities.DataReader;

public class testAvactis 
{
	HomePage homePage;
	AdminSignInPage adminSignInPage;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser)
	{
		Browser.setBrowser(browser);
		ConfigProperties.loadProperties();
		homePage = new HomePage();		
		adminSignInPage = new AdminSignInPage();
	}
	
	@AfterClass
	public void tearDown()
	{
		Browser.close();
	}
	
	@DataProvider(name="readDataFromXML")
	public Iterator<String[]> readDataFromXML()
	{
		Collection<String[]> arrData = DataReader.getDataFromXML(ConfigProperties.getProperty("DATA_FILE_PATH")+ConfigProperties.getProperty("DATA_FILE_XML"));
    	return (arrData.iterator());	
	}
	
	@Test(groups={"Login"})
	public void testStoreSignIn()
	{
		homePage.get();	
		SignInPage signInPage = homePage.doSignOut();	
		if (signInPage == null)
		{
			signInPage = homePage.goToSignInPage();
		}
		if(signInPage != null)
		{
			boolean signIn = signInPage.doSignIn(ConfigProperties.getProperty("STORE_USERNAME"), ConfigProperties.getProperty("STORE_PASSWORD"));
			Assert.assertTrue(signIn);
		}
		else
			Assert.fail("testStoreSignIn failed");
	}
	
	@Test(groups={"Purchase"},dependsOnGroups="Login", dataProvider="readDataFromXML")
	public void testProductPurchase(String productPageContent, String mainMenuID,String mainMenuName, String subMenuID, String subMenuName, String productID, String paymentMethod, String shipmentMethod, String moreFlag) throws InterruptedException
	{
		try
		{
			homePage.get();
			ProductPage productList = homePage.goToProductPageUsingMenuAndSubMenu(mainMenuID, subMenuID);
			if(productList != null)
			{
				productList.placeOrder(productList,productID,paymentMethod,shipmentMethod,moreFlag );				
			}
		}
		catch(AssertionError e)
		{
			e.printStackTrace();
			Assert.fail("Purchase product failed...");
		}
	}
	
	@Test(dependsOnGroups="Purchase", enabled=true)
	public void testOrderVerficationByAdminUser() throws InterruptedException
	{
		String orderId = ConfigProperties.getProperty("ORDER_ID");
		adminSignInPage.get();
		AdminHomePage adminHomePage=  adminSignInPage.adminSignIn(ConfigProperties.getProperty("ADMIN_USERNAME"),ConfigProperties.getProperty("ADMIN_PASSWORD"));
		if(adminHomePage != null)
		{
			String orderIdFound = adminHomePage.searchOrder(orderId);
			Assert.assertEquals(orderIdFound, orderId);
			adminHomePage.adminSignOut();
		}
		else
		{
			Assert.fail("testOrderVerficationByAdminUser failed");
		}
		
	}

}
