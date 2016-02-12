package com.norwex.ahelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.norwex.nco.TestBase;

public class Clickables extends TestBase{

	public static WebDriver drr=null;
	
	@BeforeTest
	public void Init()
	{
		WebDriver driver=new FirefoxDriver(fp);

		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get("http://google.co.in");
	}
	
	@Test
	public static void CatchLinks() throws InterruptedException
	{
	
		 List<WebElement> demovar=drr.findElements(By.tagName("a"));
		 System.out.println(demovar.size());
		 ArrayList<String> hrefs = new ArrayList<String>(); //List for storing all href values for 'a' tag

		    for (WebElement var : demovar) {
		        System.out.println(var.getText()); // used to get text present between the anchor tags
		        System.out.println(var.getAttribute("href"));
		        hrefs.add(var.getAttribute("href")); 
		        System.out.println("*************************************");
		    }

		    //Navigating to each link
		    int i=0;
		    for (String href : hrefs) {
		        driver.navigate().to(href);
		        System.out.println((++i)+": navigated to URL with href: "+href);
		        Thread.sleep(3000); // To check if the navigation is happening properly.
		        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		    }
	
	}
}
