package com.norwex.pws;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.TestBase;



public class PwsShop extends TestBase 
{
	PwsBasics p = new PwsBasics();
	
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			this.Short(2);
			driver.manage().deleteAllCookies();
			String cookies= driver.manage().getCookies().toString();
			dr.get(CONFIG.getProperty("PWSCustomerLogin"));
			System.out.println("cookies" +cookies);
		}
	
	@Test(priority=1)
	public void SetAccount() throws InterruptedException, IOException
	{
		this.Short(3);
		p.CreateAccount();
	}
	
	@Test(priority=2)
	public void Shop() throws InterruptedException
	{
		this.NavigateToShop();
	}
	
	//@Test(priority=4)
	public void AttachToParty() throws InterruptedException
	{
		getpws("AddToParty").click();
		dr.findElement(By.xpath("//*[@id='pws-search-party-form']/div/span/input")).sendKeys("March");
	    this.Short(2);
		dr.findElement(By.xpath("//ul[@class='ui-corner-all ui-state-focus']")).click();
	    System.out.println("Stopped process!.. ");
	    this.Short(30);
	}

	@Test(priority=6)
	public void OrderItems() throws InterruptedException
	{
		dr.findElement(By.xpath("//*[@id='OrderHeader']/div[1]/div[1]/ul/li[12]/a")).click();
		
		this.addProducts("Blue Diamond", "Window Cloth", "Descaler", 1);
	}

	@Test(priority=8, dependsOnMethods={"OrderItems"})
	public void UpdateCart() throws InterruptedException
	{
		this.Short(2);
		getpws("ViewCart").click();
		System.out.println("!--- Updating Items ---!");
		this.UpdateItem(2);
		this.Short(1);
		this.RemoveItem();
	}
	
	@Test(priority=10, dependsOnMethods={"OrderItems"})
	public void CheckOut() throws InterruptedException
	{
		this.Short(2);
		System.out.println("!--- Check out ---!");
		getpws("checkout_link").click();
		getpws("continue_button").click();
		
	}
	
	@Test(priority=12, dependsOnMethods={"CheckOut"})
	public void PayForOrders() throws InterruptedException
	{
		System.out.println("!----- Pay for Order -----!");
		this.refresh();
		getpws("AddPayment_button").click();
		getpws("Continue_button2").click();
		getpws("Finish_button").click();
	}
	
	@Test(priority=14, dependsOnMethods={"PayForOrders"})
	public void DisplaySummary()
	 {
		 String ProductSubtotal = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[1]/div[2]")).getText();
		 System.out.println("Product Subtotal : "+ProductSubtotal);
		   
		 String Shipping = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[2]/div[2]")).getText();
		 System.out.println("Shipping : "+Shipping);
		   
		 String Tax = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[3]/div[2]")).getText();
		 System.out.println("Tax : "+Tax);
		   
		 String OrderTotal  = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[4]/div[2]")).getText();
		 System.out.println("Order Total : "+OrderTotal);
		   
		 String ConsultantDiscApplied  = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[5]/div[2]")).getText();
		 System.out.println("Consultant Discount Applied : "+ConsultantDiscApplied);
		   
		 String CCpayments = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[6]/div[2]")).getText();
		 System.out.println("Credit Card Payments : "+CCpayments);
		   
		 String AmtDueNorwex = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[7]/div[2]")).getText();
		 System.out.println("Amount Due Norwex : "+AmtDueNorwex);
	   }
	
	
	/**************************************************************************************************************
	***************************************************************************************************************
	***************************************************************************************************************
	*************************************** Service Methods *******************************************************
	***************************************************************************************************************
	***************************************************************************************************************
	**************************************************************************************************************/
	
	 public void NavigateToShop() throws InterruptedException
	 { driver.findElement(By.xpath("//*[@id='shopnow']/a")).click(); }

		private void addProducts( String item1, String item2, String item3, int i) throws InterruptedException
			{
			System.out.println("!--- Adding products ---!");
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item1);    // item1
			this.tab();	this.tab();	this.tab(); this.tab();
			this.enter();
			this.Short(2);
			
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item2);    // item2
			this.tab();	this.tab();	this.tab(); this.tab();
			this.enter();
			this.Short(2);
		
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item3);    // item3
			this.tab();	this.tab();	this.tab(); this.tab();
			this.enter();
			this.Short(2);
			}
		
	 private void UpdateItem(int i) throws InterruptedException
	 {
		 getpws("Edit_icon").click();
		 
		 getpws("EditQty_input").clear();
		 getpws("EditQty_input").sendKeys(String.valueOf(i));
		 this.Short(1);
		 getpws("ConfirmUpdate").click();
	 }

	 private void RemoveItem() throws InterruptedException
	 {
		 System.out.println("!--- Deleting Items ---!");
		 getpws("Delete_icon").click();
		 this.Short(1);
		 getpws("ConfirmDelete_button").click();
	 }
	 
	 private void Payment()
	{
		Select line1 = new Select(driver.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_month")));
		line1.selectByVisibleText("Feb");
		Select line2 = new Select(driver.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_year")));
		line2.selectByVisibleText("2017");
		getpws("amountTocharge").sendKeys("15.00");
		getpws("AddPayment_button").click();
	}

}






