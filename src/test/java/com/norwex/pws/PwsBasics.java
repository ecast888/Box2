package com.norwex.pws;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.TestBase;

	public class PwsBasics extends TestBase 
	{
	    public static String pwsfirstName = "Deandre";
	    public static String pwslastName = "Price";
	    public static String password = "testing123";  
	    public static String password2 = "testing123";
	    public static String email = "Test"+rand+"@norwex.com";
		public static String phone = "2158990899";   
		public static String address= "2605 SW H ave";
		public static String address2= "beside river creek";
		public static String zip= "73505";
		
		@BeforeTest
		public void Authenticate() throws IOException, InterruptedException 
			{   
			initialize();
			this.Short(2);
			driver.manage().deleteAllCookies();
			dr.get(CONFIG.getProperty("PWSCustomerLogin"));
			}
		
		@Test (priority =1)
		public void CreateAccount() throws IOException, InterruptedException 
		{   
			this.Short(4);
			//getpws("customerlogin").click();
			getpws("CreateAccount_link").click();    //PWSCustomerLogin
			getpws("FirstName_input").sendKeys(pwsfirstName);
			getpws("LastName_input").sendKeys(pwslastName);
			getpws("Password_input").sendKeys(password);
			getpws("Password2_input").sendKeys(password2);
			getpws("Email_input").sendKeys(email);
			getpws("Phone_input").sendKeys(phone);
			getpws("Address1_input").sendKeys(address);
			getpws("Address2_input").sendKeys(address2);
			getpws("Zip_input").sendKeys(zip);
			getpws("Search_button").click();
			this.Short(2);
			getpws("AddressConfirm_button").click();
			this.Short(2);
			getpws("Submit_button").click();
			System.out.println("pre status check ...");
			this.Short(15);
			
			if(isElementPresent(By.xpath("//*[@id='search-all-products']"))) //
			{
			System.out.println("--- New account successful! ---");
			System.out.println("username : "+email);
			System.out.println("password : "+password);
			}
			else
			{  
				System.out.println("--- New account failed to register ---");
				getpws("Continue_button").click();
				Assert.fail();
			}
	}
		public static String setUser(String userN)
		{
			return userN;
		}
		public String getUser()
		{
			return email;
		}
		
		@Test (priority=2)
		public void ConsultantLogin() throws InterruptedException
		{
			dr.get(CONFIG.getProperty("PwsLoginPage"));  // PWS main login
			
			WebElement next =dr.findElement(By.xpath("//*[@id='top-menu']/nav/section/ul/span/ul/li[2]/ul/li[3]/a"));
			JavascriptExecutor ns = (JavascriptExecutor)driver;
			ns.executeScript("arguments[0].click();", next);
			
			getobject("consultantid").sendKeys(CONFIG.getProperty ("ncousername"));
			getobject("password").sendKeys(CONFIG.getProperty ("ncopassword"));
			getobject("login").click();
			
			if(isElementPresent(By.xpath("//*[@id='notifications']/div/a[1]/span"))) // check if alert box appears
			{
			System.out.println("!---- Consultant Login successful ----!");
			}
		else
			{  		
			System.out.println("---- Consultant Login Failed! ----");
			Assert.fail();
			}
		}
		
		//@Test (priority=3)
		public void CustomerLogin() throws InterruptedException
		{
			dr.get(CONFIG.getProperty("PwsLoginPage"));
			WebElement tnext =dr.findElement(By.xpath("//*[@id='top-menu']/nav/section/ul/span/ul/li[2]/ul/li[2]/a"));
			JavascriptExecutor tns = (JavascriptExecutor)driver;
			tns.executeScript("arguments[0].click();", tnext);
			
			getobject("consultantid").sendKeys(getUser());  // using getters and setters from a pws create class
			getobject("password").sendKeys("testing123");
			getpws("login").click();
			
			if(isElementPresent(By.xpath("//*[@id='topbarmenu']/section/ul/span/ul/li/a"))) // check if alert box appears
			{
			System.out.println("!---- Customer Login successful ----!");
			}
		else
			{  		
			System.out.println("!---- Customer Login Failed ----!");
			Assert.fail();
			}
		this.Short(2);
		}
		
}






