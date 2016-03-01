package com.norwex.nco;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Menus extends TestBase
{
	//Menus e= new Menus(); 
	
	public void financialpage() throws InterruptedException
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[2]/ul/li[7]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	public void enrollmentpage()
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[3]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	public void CustomerReports()
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[2]/ul/li[7]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	/***************************************** CONTACTS **********************************************/
	public void addcontactpage()
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[2]/ul/li[3]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	public void searchcontactpage()
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[2]/ul/li[3]/ul/li[2]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	/***************************************** ORDERS **********************************************/
	
	public void partyorder() throws InterruptedException
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[4]/ul/li[2]/ul/li[2]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	public void custormerorder() throws InterruptedException
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[4]/ul/li[2]/ul/li[5]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	public void pilorder() throws InterruptedException
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[4]/ul/li[2]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	public void shoppingspree() throws InterruptedException
	{
		WebElement Addbutton =dr.findElement(By.xpath("html/body/div[1]/div/div/div[1]/ul/div/nav/section/div/ul/ul/li[4]/ul/li[2]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
}
