package com.norwex.nco;
import org.testng.annotations.BeforeTest;
import com.norwex.nco.Menus;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.norwex.logins.*;
 
 public class Reports extends TestBase
 {
		Menus m= new Menus();
		
	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
		}
	@Test(priority=1)
	public void Navigate() throws InterruptedException
		{
			m.CustomerReports();
		}
	@Test(priority=3)
	public void ProductSales() throws InterruptedException
		{
		
		Select con = new Select(dr.findElement(By.id("nco_sales_reports_results_ReportType")));
	    con.selectByVisibleText("Top Product Sales"); 
		Select lang = new Select(getrep("datedropdown"));
		lang.selectByVisibleText("Last 30 Days");
		this.Short(3);
		getrep("viewreport").click();
		this.Short(3);
		
			if(dr.getPageSource().contains("Dish Cloth - Blue"))
			{
				System.out.println("!--- Product sale report generated ---!");
			}
			else
			{
				System.out.println("!--- Product sale report failed to generate ---!");
				Assert.fail();
			}
		} 
	
	@Test(priority=5)
	public void CustomerSales() throws InterruptedException
		{
		 
		Select con = new Select(getrep("reportdropdown"));
	    con.selectByVisibleText("Top Customer Sales"); 
		Select lang = new Select(getrep("datedropdown"));
		lang.selectByVisibleText("Last 30 Days");
		this.Short(3);
		getrep("viewreport").click();
		this.Short(3);
		
			if(dr.getPageSource().contains("Contact"))
			{
				System.out.println("!--- Customer sales report generated ---!");
			}
			else
			{
				System.out.println("!--- Customer sales report failed ---!");
				Assert.fail();
			}
		} 
	
	@Test(priority =7)
	public void SkuReport() throws InterruptedException
		{
		 
		Select con = new Select(getrep("reportdropdown"));
	    con.selectByVisibleText("SKU Report"); 
		Select lang = new Select(getrep("datedropdown"));
		lang.selectByVisibleText("Last 30 Days");
		this.Short(3);
		getrep("viewreport").click();
		this.Short(3);
		
			if(dr.getPageSource().contains("SKU"))
			{
				System.out.println("!--- SKU report generated ---!");
			}
			else
			{
				System.out.println("!--- SKU report failed ---!");
				Assert.fail();
			}
		} 
	
	

 }
 
 
 
 