package com.norwex.ahelper;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.norwex.nco.TestBase;


public class Triming extends TestBase {
	
	public static String email="e@mail.com";

	  	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");     
	    Date date = new Date();  
	    String random_number = dateFormat.format(date);  
	    
	
	
	@Test (priority =2)
    public void testapp2()
    {
		try{
		Assert.assertEquals("value3", "value1");
		}catch(Throwable t){
    	System.out.println("something went wrong");
    	Assert.fail(); //Don't continue this test.
    	System.out.println("nextline");
		}
		System.out.println("nothing went wrong");
    }
	

    		
	@Test (priority = 4)
	public void checkStrings()
	{
		//shippingDisplayed= getobject("ShippingAddress_displayed").getText().toString();
		//shippingDisplayed=shippingDisplayed.substring(0, shippingDisplayed.length() -1);	
		String address= "2503 miami drive";
		
		// only pick from first to a certain index
		System.out.println("substring 0, 11 is : "+ address.substring(0, 11)); 
		
		// take string and cut the last character
		System.out.println("address : "+ address.toString()); 
		System.out.println("address with deleted last char is :"+ address.substring(0, address.length() -1)); 
			
	}
		 @Test
		 public void start() {
		 System.out.println("Starting the server");
		 }
		 @Test(dependsOnMethods = { "start" })
		 public void init() {
	
		 System.out.println("Initializing the data for processing!");
		 }
		 @Test(dependsOnMethods = { "start", "init" })
		 public void process() {
		 System.out.println("Processing the data!");
		 }
		 @Test(dependsOnMethods = { "process" })
		 public void stop() {
		 System.out.println("Stopping the server");
		 }

	
}








