package com.norwex.pcs;
import com.norwex.nco.*;
import com.norwex.logins.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CustomerOrder extends TestBase
	{
		private static String firstName = "Deane";
	  	private static String lastName = "Deondre";
	  	private static String email= "DD"+rand+"@aol.com";
	  	private static String Homephone = "4695152200";
		private static String mobile = "4615154700";
		private static String address1= "2001 Oriental Blvd";
		private static String address2= "Apt 89a";
		private static String zip = "11235";
		Menus m = new Menus();
		 
	@BeforeTest ()
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
		}
	@Test (priority=1)
	public void Navigate() throws InterruptedException
		{ 
			this.Short(4);
			m.custormerorder();
		}
	@Test (priority=2)
	public void UpdateContactInfo() throws InterruptedException
		{
			getobjectB("CDFirstName_input").clear();
			getobjectB("CDFirstName_input").sendKeys(firstName);
			getobjectB("CDLastName_input").clear();
			getobjectB("CDLastName_input").sendKeys(lastName);
			getobjectB("CDEmail_input").clear();
			getobjectB("CDEmail_input").sendKeys(email);
			getobjectB("CDHomePhone_input").clear();
			getobjectB("CDHomePhone_input").sendKeys(Homephone);
			getobjectB("CDMobile_input").clear(); 
			getobjectB("CDMobile_input").sendKeys(mobile);
			getobjectB("CDAddress1_input").clear();
			getobjectB("CDAddress1_input").sendKeys(address1);
			getobjectB("CDAddress2_input").clear();
			getobjectB("CDAddress2_input").sendKeys(address2);
			getobjectB("CDZipCode_input").clear();
			getobjectB("CDZipCode_input").sendKeys(zip);
			getobjectB("CDZipSearch_button").click();
			this.Short(2);
			getobjectB("Alertbox_yes").click();
			this.Short(2);
			getobjectB("CDNext_button").click();   	//proceed to next page
		}
	

	//@Test (priority=3)
	public void ConnectoParty() throws InterruptedException
		{ 
		//PartyOrder a= new PartyOrder();
		String ExtractedPartyName=A_Party.newpartyName;
		System.out.println("Extracted party : "+ExtractedPartyName);
		getobjectB("CDAddordertoParty_button").click();
		getobjectB("SelectParty_dropdown").sendKeys("reference party");
		}
	
	
	@Test (priority=4)
	public void AddOrders() throws InterruptedException
		{ 
		this.Short(1);
		this.AddProduct("1120 - Dryer Balls - $20.99", 1);
		this.AddProduct("357053 - Bottle Brush Sleeve - $5.99", 1);
		this.AddProduct("1000 - Dish Cloth - White - $9.99", 1);
		this.AddProduct("1002 - Dish Cloth - Blue - $9.99", 1);
		this.Short(2);
		}
	@Test (priority=5)
	public void EditOrders() throws InterruptedException
		{   
		this.refresh();
		this.EditQuantity(1);
		this.RemoveItem();
		}
	
	@Test (priority= 6)
	public void SubmitPayment() throws InterruptedException
		{
		this.refresh();
		this.PayWithCredit();
		}
	

	@Test (priority=7)
	public void DeletePay() throws InterruptedException
		{
			getobjectB("CDDeleteCard_icon").click();
			getobjectB("CDDeletePay_confirm").click();
			this.PayWithCredit();
		}
	
	@Test (priority=8)
	public void CompleteOrder()
		{
			getobjectB("CDNext_button2").click();
			getobjectB("CDNext_button2").click();
		}
	
	
	/******************************************************************************************************
	*********************************** Service Methods ***************************************************
	*******************************************************************************************************/
	
	public void AddProduct(String item, int i) throws InterruptedException
	{
		getobject("SearchItem_input").clear();
		getobject("SearchItem_input").sendKeys(item);
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys(String.valueOf(i)); 
		this.Short(1);   
		getobject("Add_button").click();   // from OR1.properties
		this.Short(1);
	}
	public void EditQuantity(int i)
	{
		getobjectB("SPEdit_icon").click();
		getobjectB("SPChangeQty_input").clear();
		getobjectB("SPChangeQty_input").sendKeys(String.valueOf(i));
		getobjectB("SPUpdate_button").click();
	}
	public void RemoveItem()
	{
		getobjectB("SPDelete1_icon").click();
		getobjectB("SPDelete2_button").click();
	}
	
/*------------------------------------ Payments -----------------------------------------------------*/
	
	private void PayWithCredit() throws InterruptedException
	{
		this.Short(2);
		Select con =  new Select(dr.findElement(By.id("norwex_maxbundle_payment_paymentmethod")));
		con.selectByVisibleText("Credit Card"); 
		
		Select m =  new Select(dr.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_month")));
		m.selectByVisibleText("Jan"); 
		
		Select yr = new Select(dr.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_year")));
		yr.selectByVisibleText("2017"); 
		
		this.Short(2);
		getobject("Add_button2").click();
	}
	
	private void PayWithAccountBalance() throws InterruptedException    // Element is hidden *Use JavaScript Executor
	{
		Select con = new Select(getobject("CDPaymentdropdown"));
		con.selectByIndex(1);
		this.Short(2);
		WebElement Addbutton =dr.findElement(By.id("norwex_maxbundle_payment_submit"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	
	
}
