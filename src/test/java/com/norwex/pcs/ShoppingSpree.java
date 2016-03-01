package com.norwex.pcs;
import com.norwex.nco.Menus;
import com.norwex.nco.TestBase;
import com.norwex.logins.Util;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ShoppingSpree extends TestBase 
		{
			private static String firstName = "Derek";
		  	private static String lastName = "Powell";
		  	private static String preferredName = " ";
		  	private static String email= "DP"+rand+"@aol.com";
			private static String mobile = "4695152200";
			private static String zip = "75080";
			private static String address1= "851 Greenside Dr";
			private static String address2= "beside river creek";
			Menus m= new Menus();
		
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
	    }
	
	@Test (priority=1)  // @Test(invocationCount = 10)
	public void NavigateToShoppingSpree() throws InterruptedException
		{
			m.shoppingspree();
		}

	public void UpdateContactInfo() throws InterruptedException
	{
		getobjectB("SPFirstName_input").clear();
		getobjectB("SPFirstName_input").sendKeys(firstName);
		getobjectB("SPLastName_input").clear();
		getobjectB("SPLastName_input").sendKeys(lastName);
		getobjectB("SPPrefferedName_input").clear();
		getobjectB("SPPrefferedName_input").sendKeys(preferredName);
		getobjectB("SPEmail_input").clear();
		getobjectB("SPEmail_input").sendKeys(email);
		getobjectB("SPMobile_input").clear();
		getobjectB("SPMobile_input").sendKeys(mobile);
		getobjectB("SPAddress1_input").clear();
		getobjectB("SPAddress1_input").sendKeys(address1);
		getobjectB("SPAddress2_input").clear();
		getobjectB("SPAddress2_input").sendKeys(address2);
		getobjectB("SPZipcode_input").clear();
		getobjectB("SPZipcode_input").sendKeys(zip);
		getobjectB("SPZipSearch_button").click();
		getobjectB("SPAddressValidateAlert_button").click();
		this.Short(2);
		getobjectB("SPNext_button").click();   	// proceed to next page
	}
	@Test (priority=2)
	public void AddOrders() throws InterruptedException
		{
		this.AddProduct("1002 - Blue  Dish Cloth - Blue - $9.99", 1);
		this.AddProduct("354000 - Spray Bottle - $6.99", 2);
		this.refresh();
		}
	@Test (priority=4)
	public void EditQuantity() throws InterruptedException
		{   
			this.EditQuantity(3);
		}
	@Test (priority=5)
	public void DeleteItem() throws InterruptedException
		{
			this.Remove();
		}
	
	@Test (priority=6)
	public void CompleteOrder()
		{
			getobjectB("SPNext2_button").click();
			getobjectB("SPSubmit_button").click();
		}
	
	@Test (priority=6)
	public void VerifyPdf() throws InterruptedException
		{
			getobjectB("Pdficon").click();
			this.Short(2);
			
			if(isElementPresent(By.id("notifications"))) // 
			{System.out.println("!---- No app error on PDF ----!"); }
		else
			{ System.out.println("!--- Something went wrong ---!");
			Assert.fail();
			}
		}


	/******************************************************************************************************
	*********************************** Service Methods ***************************************************
	*******************************************************************************************************/
	
	public void AddProduct(String item, int i) throws InterruptedException
	{
		getobjectB("SPItem_input").clear();
		getobjectB("SPItem_input").sendKeys(item);
		getobjectB("SPQty_input").clear();
		getobjectB("SPQty_input").sendKeys(String.valueOf(i)); 
		this.Short(2);   
		getobject("Add_button").click();
	}
	
	public void EditQuantity(int i) throws InterruptedException
	{
		getobjectB("SPEdit_icon").click();
		this.Short(2);
		getobjectB("SPChangeQty_input").clear();
		getobjectB("SPChangeQty_input").sendKeys(String.valueOf(i));
		getobjectB("SPUpdate_button").click();
	}
	
	public void Remove() throws InterruptedException
	{
		getobjectB("SPDelete1_icon").click();
		this.Short(2);
		getobjectB("SPDelete2_button").click();
	}
}





















