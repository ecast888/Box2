package com.norwex.ahelper;
import org.openqa.selenium.By;
import com.norwex.nco.TestBase;


public class FindElementBy extends TestBase {
	
	String a = "anyname";
	
	public void partialLink()
	{
	 dr.findElement(By.partialLinkText(a)).click();
	}
}