package com.norwex.pws;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.TestBase; 
import java.io.IOException;

public class PwsLogins extends TestBase
{
	PwsCreate newid = new PwsCreate();
	
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
		initialize();
		//Util.pwsLogin(); 
		dr.get(CONFIG.getProperty("PwsLoginPage"));

		}
	
	
	@Test (priority=1)
	public void ConsultantLogin() throws InterruptedException
	{
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
	
	@Test (priority=2)
	public void CustomerLogin() throws InterruptedException
	{
		dr.get(CONFIG.getProperty("PwsLoginPage"));
		WebElement tnext =dr.findElement(By.xpath("//*[@id='top-menu']/nav/section/ul/span/ul/li[2]/ul/li[2]/a"));
		JavascriptExecutor tns = (JavascriptExecutor)driver;
		tns.executeScript("arguments[0].click();", tnext);
		
		System.out.println("extracted user: "+newid.getUser());
		
		getobject("consultantid").sendKeys(newid.getUser());  // using getters and setters from a pws create class
		this.Short(4);
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
