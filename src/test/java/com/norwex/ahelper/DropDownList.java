package com.norwex.ahelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DropDownList {

	@Test
	public void dropdown() throws InterruptedException 
	{
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("http://test1.absofttrainings.com/shop/");
		driver.findElement(By.name("orderby"));
		
		Select dropdown= new Select(driver.findElement(By.name("orderby")));
				
		System.out.println("Default option: " + 	dropdown.getFirstSelectedOption().getText());
		
		
		
		//----------------------------------------------------------------------------
		Thread.sleep(1000);
		dropdown.selectByVisibleText("Sort by average rating");
		dropdown = new Select(driver.findElement(By.name("orderby")));

		System.out.println("Currently selected:" + 
		dropdown.getFirstSelectedOption().getText());

		dropdown.selectByValue("popularity");
		dropdown = new Select(driver.findElement(By.name("orderby")));
		
		System.out.println("Currently selected:" + 
				dropdown.getFirstSelectedOption().getText());
		
	}
}