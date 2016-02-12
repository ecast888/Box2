package com.norwex.nco;

import java.io.IOException;

import org.openqa.selenium.By;
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
			dr.get("https://nwxtest.com/en_US/consultant/logout");
	    }

	@Test(priority =1)
	public void Navigate() throws InterruptedException
		{
			WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'Connect With A Consultant')]"));
			Actions action = new Actions(dr);
			action.click(menu).perform();
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
			this.close(15);
		}
}
