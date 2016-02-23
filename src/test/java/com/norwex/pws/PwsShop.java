package com.norwex.pws;
import java.io.IOException;
import com.norwex.pws.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.Menus;
import com.norwex.nco.TestBase;



public class PwsShop extends TestBase 
{
	PwsCreate p = new PwsCreate();
	
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			//TestUtility.pwsLogin();
		}
	
	@Test(priority=1)
	public void SetAccount() throws InterruptedException, IOException
	{
		p.SignUp();
	}
	
	@Test(priority=2)
	public void Shop() throws InterruptedException
	{
		NavigateToShop();
	}
	
	@Test(priority=4)
	public void AttachToParty() throws InterruptedException
	{
		getpws("AddToParty").click();
		Select con = new Select(driver.findElement(By.id("form_search")) );
	    con.selectByVisibleText("Feb22155835");
	    this.Short(15);
	    System.out.println("Stopped process!.. ");
	    this.Short(30);
	}

	@Test(priority=6)
	public void OrderItems() throws InterruptedException
	{
		this.addProducts("Bottle Brush Sleeve", "Bottle Brush Sleeve", "Spirinetts", "Descaler", 1);
	}

	@Test(priority=8, dependsOnMethods={"OrderItems"})
	public void UpdateCart() throws InterruptedException
	{
		this.Short(2);
		getpws("ViewCart").click();
		this.UpdateItem(2);
		this.Short(1);
		this.RemoveItem();
	}
	
	@Test(priority=10, dependsOnMethods={"OrderItems"})
	public void CheckOut() throws InterruptedException
	{
		this.Short(2);
		getpws("checkout_link").click();
		
		System.out.println("!----- Check if login is required -----!");
		
		if(isElementPresent(By.xpath("//input[@id='username']"))) // check if log page appears
				{
			System.out.println("!----- Turns out login is required -----!");
			getpws("Username_input").sendKeys(CONFIG.getProperty ("username"));
			getpws("Password_input").sendKeys(CONFIG.getProperty ("password"));
			getpws("Login_button").click();
				}
		else
		{  
			System.out.println("!----- Login Not required -----!");
			//getpws("checkout_button").click();
			getpws("continue_button").click();
		}
	}
	
	@Test(priority=12, dependsOnMethods={"CheckOut"})
	public void PayForOrders() throws InterruptedException
	{
		System.out.println("!----- Pay for Orders -----!");
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
	
//	@AfterTest
//	public void closebrowser() throws InterruptedException
//		{
//			//this.close(15);
//		}
	
	/**************************************************************************************************************
	***************************************************************************************************************
	***************************************************************************************************************
	*************************************** Service Methods *******************************************************
	***************************************************************************************************************
	***************************************************************************************************************
	**************************************************************************************************************/
	
	 public void NavigateToShop() throws InterruptedException
	 { driver.findElement(By.xpath("//*[@id='shopnow']/a")).click(); }

		private void addProducts( String item1, String item2, String item3, String item4, int i) throws InterruptedException
			{
			System.out.println("*****Adding products******");
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item1);    // item1
			this.tab();	this.tab();	this.tab();
			this.enter();
			this.Short(2);
			
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item2);    // item2
			this.tab();	this.tab();	this.tab();
			this.enter();
			this.Short(2);
		
			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item3);    // item3
			this.tab();	this.tab();	this.tab();
			this.enter();
			this.Short(2);
			

			getpws("SearchItem_input").clear();
			getpws("SearchItem_input").sendKeys(item4);    // item4
			this.tab();	this.tab();	this.tab();
			this.enter();
			this.Short(2);
			}
		
	 private void UpdateItem(int i) throws InterruptedException
	 {
		 System.out.println("****Updating Items******");
		 getpws("Edit_icon").click();
		 
		 getpws("EditQty_input").clear();
		 getpws("EditQty_input").sendKeys(String.valueOf(i));
		 this.Short(1);
		 getpws("ConfirmUpdate").click();
	 }

	 private void RemoveItem() throws InterruptedException
	 {
		 System.out.println("*****Deleting Items******");
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






