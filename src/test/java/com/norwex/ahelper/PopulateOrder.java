package com.norwex.ahelper;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.norwex.nco.TestBase;

public class PopulateOrder extends TestBase {

	
	@Test
	public void AddOrders() throws InterruptedException
	{
		
		// Page 2: choose items to order
		// item 1
		System.out.println("*****Add shopping spree orders ******");
		getobject("SearchItem_input").clear();
		getobject("SearchItem_input").sendKeys("envi");
		System.out.println("visible? :"+dr.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']")).getText());
		Thread.sleep(2000);   
		getobject("GuestItem_Ajax").click();
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys("1"); 
		Thread.sleep(2000);   
		getobject("Add_button").click();
	
}
	
}
