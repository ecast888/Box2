package com.norwex.nco;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Menus extends TestBase
{
	//Menus e= new Menus(); 
	
	public void financialpage() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Business')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'My Profile')]"));
		WebElement childmenu= dr.findElement(By.xpath("//a[contains(text(), 'Financial')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform(); 
		action.moveToElement(submenu).perform();
		action.click(childmenu).perform();
	}
	
	public void enrollmentpage()
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Team')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Sign Up a New Consultant')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform();  
		action.click(submenu).perform();
	}
	
	public void CustomerReports()
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Business')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Customer Reports')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform();  
		action.click(submenu).perform();
	}
	
	/***************************************** CONTACTS **********************************************/
	public void addcontactpage()
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Business')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Contacts')]"));
		WebElement sublink= dr.findElement(By.xpath("//a[contains(text(), 'Add Contact')]"));
		Actions action = new Actions(dr);
		
		action.moveToElement(menu).perform();
		action.moveToElement(submenu).perform();
		action.click(sublink).perform();
	}
	public void searchcontactpage()
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Business')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Contacts')]"));
		WebElement sublink= dr.findElement(By.xpath("//a[contains(text(), 'Search Contacts')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform();
		action.moveToElement(submenu).perform();
		action.click(sublink).perform();
	}
	/***************************************** ORDERS **********************************************/
	public void partyorder() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Orders')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Create a New Order')]"));
		WebElement childmenu= dr.findElement(By.xpath("//a[contains(text(), 'Party Order')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform(); 
		action.moveToElement(submenu).perform();
		action.click(childmenu).perform();
	}
	
	public void custormerorder() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Orders')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Create a New Order')]"));
		WebElement childmenu= dr.findElement(By.xpath("//a[contains(text(), 'Customer Order')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform(); 
		action.moveToElement(submenu).perform();
		action.click(childmenu).perform();
	}
	
	public void pilorder() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Orders')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Create a New Order')]"));
		WebElement childmenu= dr.findElement(By.xpath("//a[contains(text(), 'Personal Supply Order')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform(); 
		action.moveToElement(submenu).perform();
		action.click(childmenu).perform();
	}
	public void shoppingspree() throws InterruptedException
	{
		WebElement menu= dr.findElement(By.xpath("//a[contains(text(), 'My Orders')]"));
		WebElement submenu= dr.findElement(By.xpath("//a[contains(text(), 'Create a New Order')]"));
		WebElement childmenu= dr.findElement(By.xpath("//a[contains(text(), 'Shopping Spree Order')]"));
		Actions action = new Actions(dr);
		action.moveToElement(menu).perform(); 
		action.moveToElement(submenu).perform();
		action.click(childmenu).perform();
	}
}
