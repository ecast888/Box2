package com.norwex.nco;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindConsultant extends TestBase {
	
	
	@BeforeTest
    public void Authenticate() throws IOException, InterruptedException
	    {   
			initialize();
			dr.get(CONFIG.getProperty("NcoLoginPage"));
	    }

	@Test(priority =1)
	public void Navigate() throws InterruptedException
		{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/li[6]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
		}
	
	@Test(priority =2)
	public void PerformSearch() throws InterruptedException
		{
			this.SearchByName("alexy", "desouza");
		}
	
	public void SearchByName(String fname, String lname ) throws InterruptedException
		{
			getobject("FName_input").sendKeys(fname);
			getobject("LName_input").sendKeys(lname);
			getobject("Captcha_input").sendKeys("1234");
			getobject("Searchbutton_input").click();
		}
	@AfterTest
	public void closebrowser() throws InterruptedException
		{
			//this.close(15);
		}
}
