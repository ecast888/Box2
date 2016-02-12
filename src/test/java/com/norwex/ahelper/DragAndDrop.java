package com.norwex.ahelper;
import com.norwex.nco.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop extends TestBase {
	
	
	public void DragDrop()
	{
			WebElement element = driver.findElement(By.xpath("//*[@id='OrderHeader']/div[2]/div/div[3]/div/div/div[1]/div[1]/div[1]/div[1]"));
			WebElement target = driver.findElement(By.xpath("//*[@id='OrderHeader']/div[1]/div[1]/div/fieldset/div/span"));
			(new Actions(driver)).dragAndDrop(element, target).perform();

	}

}
