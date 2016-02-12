package com.norwex.ahelper;
import java.util.List;
import com.norwex.nco.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class IsElementpresent extends TestBase
{
	
	public boolean isElementsPresent(String xpathExpression)
	{
		List<WebElement> allElements= driver.findElements(By.xpath(xpathExpression));
		if(allElements.size()==0)
			return false;
		else
			return true;
	}
	
	public boolean isElementPresent(By by) 
	{
	    try {
	      driver.findElement(by);
	      return true;
	        } catch (org.openqa.selenium.NoSuchElementException e) 
	    {
	      return false;
	    }
     }
	
/*---------------------------------------------------------------------------------------------------------------*/	
	public void CheckOut() throws InterruptedException
	{
		getpws("Checkout_link").click();
		
		System.out.println("*****Check Login requirement******");
		if(isElementPresent(By.xpath("html/body/div[2]/div/div[5]/div[2]")))
		{
			/* do something */
		}
		else
		{
			 /* do something else */
		}
}
/*---------------------------------------------------------------------------------------------------------------*/
		public boolean isAlertPresent() 
		{ 
		    try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }   // try 
		    catch (NoAlertPresentException Ex) 
		    { 
		        return false; 
		    }   // catch 
		} 	
		
		protected boolean TextIsPresent(String text){
		    try{
		        boolean b = driver.getPageSource().contains(text);
		        return b;
		    }
		    catch(Exception e){
		        return false;
		    }
		  }
}
