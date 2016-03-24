package com.norwex.nco;
import com.norwex.logins.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.norwex.nco.TestBase;


public class Enrollment_CA extends TestBase 
{
	    public static String Region = "Canada";
	    public static String firstName = "Paul";
	    public static String lastName = "Banabas";
	    public static String prefferedName="Mat";
	    public static String email = "MT"+rand+"a@hotmail.com";
	    public static String homephone= "2605562470";  
	    public static String mobilephone = "3300047811";  
	    public static String birthdate = "06/15/1988";  
		public static String ssn = "114257787";
		public static String address= "871 whitmore ave east";
		public static String address2= "dauphin";
		public static String zip= " r7n 3b3";
		
		//public static String joincode="RECSHOP2500";
		
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
		this.Short(4);
		m.enrollmentpage();
		
		
		
		// Page 1: Country, language and JoinCode
		Select con = new Select(getobject("Country"));
	    con.selectByVisibleText(Region); 
		Select lang = new Select(getobject("Language"));
		lang.selectByVisibleText("English");
		
		//getobject("JoinCode_input").sendKeys(joincode);
		this.Short(2);
		getobject("Certify_checkbox").click();
		this.Short(2);
		getobject("Next_button").click(); 
		
		// Page 2: Personal Details
		Select prefix = new Select(driver.findElement(By.id("norwex_maxbundle_contact_luSalutationid")));
		prefix.selectByIndex(3);
		
		getobject("FirstName_input").sendKeys(firstName);
		getobject("LastName_input").sendKeys(lastName);
		getobject("PreferredName_input").sendKeys(prefferedName);
		getobject("Email_input").sendKeys(email);
		getobject("HomePhone_input").sendKeys(homephone);
		getobject("MobilePhone_input").sendKeys(mobilephone);
		getobject("birthdate_input").sendKeys(birthdate);
		
		getobject("Ssn_input").sendKeys(ssn);
		getobject("Address1_input").sendKeys(address);
		getobject("Address2_input").sendKeys(address2);
		getobject("ZipCode_input").sendKeys(zip);
		getobject("ZipSearch_button").click();
		this.Short(2);
		getobject("CA_ForceValidate_button").click();
		this.Short(2);
		getobject("SameAddress_checkbox").click();
		getobject("Next[2]_button").click();
		
		// Page 3: Office Suite Subscription
		getobject("IwishToEnroll").click();
		getobject("Next[3]_button").click();
		
		// page 4: Starter Kit Selection
		getobject("Superior_Mop_radio").click();
		getobject("Next[4]_button").click();
		
		//Page 5: Start Kit Upgrade
		getobject("CA_NoUpgrade").click();
		getobject("Next[5]_button").click();	
	}
	@Test(priority =2)
	public void Payment() throws InterruptedException
	{
		// Page 6: Details & Order summary, Payment info
		this.Short(3);
		//getpws("CardholderName").clear();
		dr.findElement(By.id("norwex_maxbundle_creditcardprofile_nameoncard")).clear();
		dr.findElement(By.id("norwex_maxbundle_creditcardprofile_nameoncard")).sendKeys("Brown Ronelo");
		
		Select exp = new Select(dr.findElement(By.id("norwex_maxbundle_creditcardprofile_expiration_month")));
		exp.selectByIndex(6);
		Select yr = new Select(dr.findElement(By.id("norwex_maxbundle_creditcardprofile_expiration_year")));
		yr.selectByValue("2017");
		getobject("Next[6]_button").click();
	}
	
	@Test(priority =2)
	public void Confirmation() throws InterruptedException
	{
		// Page 7: confirmation page
		String consultantid= getenro("consultantid").getAttribute("value").toString();
		System.out.println("Consultant ID: "+ " "+consultantid); 
	}
	
 }
	
	