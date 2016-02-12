package com.norwex.pws;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.logins.Util;
import com.norwex.nco.TestBase;

	public class PwsCreate extends TestBase 
	{
	    public static String pwsfirstName = "Pamela";
	    public static String pwslastName = "diaz";
	    public static String password = "testing123";  
	    public static String password2 = "testing123";
	    public static String email = "Test"+rand+"@norwex.com";
		public static String phone = "2158990899";   
		public static String address= "2605 SW H ave";
		public static String address2= "beside river creek";
		public static String zip= "73505";
		public static String newuser= email;
		
		@BeforeTest
		public void Authenticate() throws IOException, InterruptedException 
			{   
			initialize();
			//Util.pwsLogin(); 
			}
		
		@Test (priority =1)
		public void SignUp() throws IOException, InterruptedException 
		{   
			dr.get(CONFIG.getProperty("PwsLoginPage"));
			this.Short(2);
			getpws("customerlogin").click();
			getpws("CreateAccount_link").click();
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
			this.Short(3);
			getpws("AddressConfirm_button").click();
			this.Short(3);
			getpws("Submit_button").click();
			
			if(isElementPresent(By.xpath("//*[@id='search-all-products']"))) //
			{
			System.out.println("--- New account successful! ---");
			System.out.println("username : "+newuser);
			System.out.println("password : "+password);
			}
			else
			{  
				System.out.println("--- New account failed to register ---");
				getpws("Continue_button").click();
				Assert.fail();
			}
	}
		
//		@AfterTest
//		public void closebrowser() throws InterruptedException
//			{
//				//this.close(60);
//			}
		
}






