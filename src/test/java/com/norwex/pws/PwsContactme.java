package com.norwex.pws;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.TestBase; 



public class PwsContactme extends TestBase 
{
	    public static String firstname = "Pamela";
	    public static String lastname = "Diaz";
	    public static String email = "pdiaz23@norwex.com";
		public static String mobile = "2158990899";   
		public static String address1= "2605 SW H ave";
		public static String zip= "73505";
		
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
		initialize();
		dr.get(CONFIG.getProperty("PwsLoginPage")); 	this.Short(2);
		}
	
	@Test (priority =1)
	public void ContactForm() throws IOException, InterruptedException 
	{   
		getpws("contactme_link").click();
		getpws("firstname").sendKeys(firstname);
		getpws("lastname").sendKeys(lastname);
		getpws("email").sendKeys(email);
		getpws("mobile").sendKeys(mobile);
		getpws("address1").sendKeys(address1);
		getpws("zip").sendKeys(zip);
		getpws("zipsearch").click();	this.Short(2); 
		getpws("addressvalidate").click();
		getpws("generalinquiry_checkbox").click();
		getpws("comments").sendKeys("need to find out how to maximize discounts");
		this.Short(2);
		getpws("submit").click();
	}
	
}

