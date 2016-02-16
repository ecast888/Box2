package com.norwex.nco;
import com.norwex.logins.*;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.TestBase;
import com.norwex.nco.TestBase;


public class Enrollment_US extends TestBase 
{
	    public static String Region = "United States of America";
	    public static String firstName = "Justin";
	    public static String lastName = "Webber";
	    public static String prefferedName="JWeb";
	    public static String email = "JW"+rand+"a@hotmail.com";
	    public static String Workphone1 = "2185562478"; 
	    public static String bday= "09/18/1992";
		public static String ssn = "114257787";
		public static String address= "2605 SW H ave";
		public static String address2= "Lawton";
		public static String zip= "73505";
	    public static String joincode="HALFITEM1";
	
		Menus m= new Menus();
		
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
		initialize();
		Util.ncoLogin();
	    }
	
	@Test(priority =1)
	public void SignUp() throws InterruptedException
	{
		m.enrollmentpage();
		
		// Page 1: choose country and language
		Select con = new Select(getobject("Country"));
	    con.selectByVisibleText(Region); 
		Select lang = new Select(getobject("Language"));
		lang.selectByVisibleText("English");
		
		//  Certify am over 18 and add join code
		
		getobject("JoinCode_input").sendKeys(joincode);
		this.Short(1);
		getenro("Certify_checkbox").click();
		this.Short(1);
		getenro("Next_button").click(); 
		
		// Page 2: Personal Details
		Select prefix = new Select(driver.findElement(By.id("norwex_maxbundle_contact_luSalutationid")));
		prefix.selectByIndex(3);
		
		getenro("FirstName_input").sendKeys(firstName);
		getenro("LastName_input").sendKeys(lastName);
		getenro("PreferredName_input").sendKeys(prefferedName);
		getenro("Email_input").sendKeys(email);
		getenro("HomePhone_input").sendKeys(Workphone1);
		getenro("EnrollBirthDate_input").sendKeys(bday);
		Select businesstype = new Select(driver.findElement(By.id("norwex_maxbundle_contact_business_luBusinesstypeid")));
		businesstype.selectByIndex(0);
		getenro("Ssn_input").sendKeys(ssn);
		getenro("Address1_input").sendKeys(address);
		getenro("Address2_input").sendKeys(address2);
		getenro("ZipCode_input").sendKeys(zip);
		getenro("ZipSearch_button").click();
		this.Short(2);
		getenro("US_AddressValidateAlert_button").click();
		this.Short(2);
		getenro("SameAddress_checkbox").click();
		getenro("Next[2]_button").click();
		
		// Page 3: Office Suite Subscription
		getenro("IwishToEnroll").click();
		getenro("Next[3]_button").click();
		
		// page 4: Starter Kit Selection
															//getobject("MiniStarterPack_radio").click();   // if user elects to join for free
		getenro("Next[4]_button").click();
		
		//Page 5: Start Kit Upgrade
		getenro("Next[5]_button").click();
		
		// Page 6: Details & Order summary, Payment info
		this.Short(2);
		getenro("CardHolderName[recruit]_input").sendKeys("Ronello Brown Ronello");
		getenro("CardNumber[recruit]_input").clear();
		getenro("CardNumber[recruit]_input").sendKeys("4030000010001234");
		Select exp = new Select(dr.findElement(By.id("norwex_maxbundle_creditcardinfo_expiration_month")));
		exp.selectByIndex(6);
		Select yr = new Select(dr.findElement(By.id("norwex_maxbundle_creditcardinfo_expiration_year")));
		yr.selectByValue("2017");
		getenro("Next[6]_button").click();
		
	}
//	@AfterTest
//	public void closebrowser() throws InterruptedException
//	{
//		//this.close(15);
//	}
	
 }
	
	