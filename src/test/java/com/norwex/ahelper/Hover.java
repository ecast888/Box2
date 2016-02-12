package com.norwex.ahelper;
import com.norwex.nco.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Hover extends TestBase {

	@Test(priority =1, description="Navigates 3 subs")
	public void Navigate() throws InterruptedException
		{
				driver.navigate().to("https://max-qa-box2/en-US/consultant/dashboard");
				WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Account')]"));
				WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Contacts')]"));
				WebElement sublink= dr.findElement(By.xpath("//a[contains(text(), 'Add Contact')]"));
				
				Actions action = new Actions(dr);
				
				action.moveToElement(menu).perform();
				action.moveToElement(submenu).perform();
				action.click(sublink).perform();
		}
	@Test(priority =2, description="Navigates 2 subs")
	public void Navi() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'Team')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Sign Up a New Recruit')]"));
		
		Actions action = new Actions(dr);
		
		action.moveToElement(menu).perform();  
		action.click(submenu).perform();
	}
}


