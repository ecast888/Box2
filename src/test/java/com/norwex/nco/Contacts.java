package com.norwex.nco;
import com.norwex.logins.*;
import com.norwex.nco.Menus;
import com.thoughtworks.selenium.webdriven.commands.IsTextPresent;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
 public class Contacts extends TestBase
 {
	   // # Declare variables for *this class only
	 
	    private static String firstName = "J"+rand;
	    private static String lastName = "A"+rand;
	    private static String contactEmail= "JK"+rand+"@nwx.com";
		private static String mobile = "2151101515";
		private static String address= "2605 SW H Ave";
		private static String zip= "73505";
		Menus m= new Menus();
		
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
		}
	@Test(priority =1, description="Navigates to the User Settings page")
	public void Navigate() throws InterruptedException
		{
			this.Short(5);
			m.addcontactpage();
		}
	@Test(priority =3)
	public void AddNewContact() throws InterruptedException
		{
			// Page 1: Enter new contact
				getcon("firstname").sendKeys(firstName);
				getcon("lastname").sendKeys(lastName);
				getcon("email").sendKeys(contactEmail);
				getcon("homephone").sendKeys(mobile);
				getcon("address1").sendKeys(address);
				getcon("zip_input").sendKeys(zip);
				getcon("zipsearch_button").click();
				this.Short(2);
				getcon("confirmaddress").click();
				this.Short(2);
				getcon("sameasbilling_checkbox").click();
				this.Short(2);
				getcon("save_button").click();
				this.Short(2);
				System.out.println("firstname : "+firstName);
				System.out.println("next is element present: count from now");
		} 
	
	@Test (priority =6, dependsOnMethods={"AddNewContact"})
	public void VerifyContactSaved() throws InterruptedException
		{
			if(isElementPresent(By.xpath("html/body/div[2]/div/div[5]/div[2]"))) // check if alert box appears
				{
				System.out.println("!---- Contact Saved ----!");
				}
			else
				{  		
				System.out.println("!--- Either the contact was not saved or confirmation box is not located ---!");
				Assert.fail();
				}
			this.Short(2);
		}
	
	//@Test (priority =9)
	public void AttemptDuplicate() throws InterruptedException
	{
		
		m.addcontactpage();
		System.out.println("!---- Attempting to duplicate last contact ----!");
		getcon("firstname").sendKeys(firstName);
		getcon("lastname").sendKeys(lastName);
		getcon("email").sendKeys(contactEmail);
		getcon("homephone").sendKeys(mobile);
		getcon("address1").sendKeys(address);
		getcon("zip_input").sendKeys(zip);
		getcon("zipsearch_button").click();
		getcon("confirmaddress").click();
		getcon("sameasbilling_checkbox").click();
		this.Short(2);
		getcon("save_button").click();
		this.Short(2);
		
		if(isElementPresent(By.id("norwex_maxbundle_contact")))
		{
		System.out.println("!--- duplicate detected, unable to add contact ---!");
		}
	else
		{  		
		System.out.println("!--- something went wrong ---!");
		Assert.fail();
		}
		System.out.println("firstname : "+firstName);
	}

 
 	@Test (priority =12,  dependsOnMethods={"AddNewContact"}) // invocationCount = 100,
 	public void Findcontact() throws InterruptedException, AWTException
 		{
 			this.Short(2);
	 		m.searchcontactpage();
			this.Short(15);
			getcon("searchbox").clear();
			getcon("searchbox").sendKeys(firstName);
			this.Short(2);
			dr.findElement(By.partialLinkText(firstName)).click();   /* Partial link text */
			String newfirstname= getcon("firstname").getAttribute("value").toString();  /* use when getText() doesn't work */
			
			System.out.println("initial firstname: "+ " "+firstName); 
			System.out.println("extracted firstname: "+" "+newfirstname); 
			
			try{
				Assert.assertEquals(newfirstname, firstName);
				}catch(Throwable t){
		    	System.out.println("name match was NOT sucessful");
		    	Assert.fail();                    // If initial condition is not met, fail the test
		    	System.out.println("nextline");  // * nextline should not display in console
				}
				System.out.println("Name match found"); 
 		}
 
	@Test (priority =15,  dependsOnMethods={"AddNewContact"}) // invocationCount = 100,
 	public void Contactdelete() throws InterruptedException, AWTException
 		{
	 		this.backspace();
			this.Short(3);
			getcon("searchbox").clear();
			getcon("searchbox").sendKeys(firstName);
			this.Short(3);
			
			if(isElementPresent(By.xpath("//*[@id='contacts']/tbody/tr/td[9]/a[5]/span")))
			{
			System.out.println("!--- delete icon identified ---!");
			getcon("deletecontact").click();
			this.Short(4);
			getcon("confirmdelete").click();
			}
		else
			{  		
			System.out.println("!--- cannot identify the delete icon ---!");
			Assert.fail();
			}
 		}
 }
 
 
 
 
 
 
 
 
 