package com.norwex.nco;
import java.io.IOException;
import com.norwex.logins.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

 
 public class Financial extends TestBase 
 		{
	    public static String Percentage = "100";
	    public static String accountName = "Meline Simborio";
	    public static String bankName = "Morgan Chase";
		public static String routing = "111907445";
		public static String accountNumber = "5454545454545454";
		public static String cardHolderName= accountName;
		public static String cardNumber= accountNumber;
		public static String db_routingNumber=null;
		public static String db_accNumber=null;
		
		Menus m = new Menus();

	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
		}
	
	@Test (priority=1)
	public void NavigateToFinance() throws InterruptedException
		{
			m.financialpage();
		}
	
	@Test(priority =2)
	public void AddBankAccount() throws InterruptedException
		{
			this.Short(2);
			getobject("Percentage_input").clear();
			getobject("Percentage_input").sendKeys(Percentage); 
			Select type = new Select(driver.findElement(By.id("norwex_maxbundle_consultantpreferences_primarybankinfoid_luAccounttypeid")));
			type.selectByIndex(1);
			getobject("AccountName_input").clear();
			getobject("AccountName_input").sendKeys(accountName);
			getobject("BankName_input").clear();
			getobject("BankName_input").sendKeys(bankName);
			getobject("RoutingNumber_input").clear();
			getobject("RoutingNumber_input").sendKeys(routing);
			getobject("AccountNumber_input").clear();
			getobject("AccountNumber_input").sendKeys(accountNumber);
			this.Short(2);
			getobject("cardholdername").clear();
			getobject("cardholdername").sendKeys(accountName);
			getobject("cardnumber").clear();
			getobject("cardnumber").sendKeys(accountNumber);
			//Select by month and year
			Select exp = new Select(driver.findElement(By.id("norwex_maxbundle_creditcardinfo_expiration_month")));
			exp.selectByIndex(12);
			Select yr = new Select(driver.findElement(By.id("norwex_maxbundle_creditcardinfo_expiration_year")));
			yr.selectByValue("2019");
			getobject("cardsave").click();
		}
	
//	@AfterTest
//	public void closebrowser() throws InterruptedException
//		{
//			this.close(15);
//		}

	
//	@Test (dependsOnMethods = { "AddBankAccount" })
//	public void CheckPercentage() throws InterruptedException
	//	{
	//		this.Short(2);
	//		String InitialPercentage = driver.findElement(By.id("norwex_maxbundle_consultantpreferences_primarybankinfoid_percentage")).getText();
	//		String newBanKName = getobject("BankName_input").getText();
	//		System.out.println("Bank Name :"+newBanKName);
	//		String RemainingPercentage = getobject("Percentage2_input").getText();
	//		int Percentage1= Integer.parseInt(InitialPercentage);
	//		int Percentage2=  100 - Percentage1;
	//		
	//	    try {
	//	        Percentage1 = Integer.parseInt(InitialPercentage);
	//	    } catch (NumberFormatException e) {
	//	        //Log it if needed
	//	    }
	//		
	//		System.out.println("InitialPercentage : "+ InitialPercentage);
	//		System.out.println("RemaningPercentage : "+ RemainingPercentage);
	//		System.out.println("Percentage 1 : "+Percentage1);
	//		System.out.println("Percentage 2 : "+Percentage2);
	//		Assert.assertEquals(Percentage1, Percentage2);
	//	}
	
	
 }