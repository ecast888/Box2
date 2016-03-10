package com.norwex.pcs;
import java.io.IOException;
import com.norwex.logins.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.norwex.nco.Menus;
import com.norwex.nco.TestBase;


public class PIL extends TestBase 
{
	Menus m= new Menus();
	

	@BeforeTest
	public void Authenticate() throws IOException, InterruptedException 
		{   
			initialize();
			Util.ncoLogin();
		}

	@Test (priority=1)
	public void NavigateToPIL() throws InterruptedException
		{
			m.pilorder();  //*
		}
	
	@Test (priority=2)
	public void AddOrders() throws InterruptedException
		{ 
			this.Short(3);
			this.refresh();
			this.AddProduct("1120", 1); // Dryer balls
			this.AddProduct("357053", 2);
			this.AddProduct("1000", 1); //  Dish Cloth - White - $9.99
			this.refresh();
		}
	@Test (priority=4)     // invocationCount = 100
	public void EditQuantity() throws InterruptedException
		{   
			this.EditQuantity(1);
		}
	
	@Test (priority=5)
	public void DeleteItem() throws InterruptedException
		{
			this.Short(3);
			this.Remove();
		}

	@Test (priority=6)
	private void PayWithCredit() throws InterruptedException
	{
		this.Short(2);
		Select con =  new Select(dr.findElement(By.id("norwex_maxbundle_payment_paymentmethod")));
		con.selectByVisibleText("Credit Card"); 
		
		Select m =  new Select(dr.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_month")));
		m.selectByIndex(5);
		Select yr = new Select(dr.findElement(By.id("norwex_maxbundle_payment_creditcardprofileid_expiration_year")));
		yr.selectByVisibleText("2017"); 
		
		this.Short(2);
		getobject("Add_button2").click();
	}
	
	@Test (priority=7)
	public void CompleteOrder()
		{
			//getobjectB("PIAdd_button2").click();
			getobjectB("PINext_button").click();
			getobjectB("PISubmit_button").click();
		}
	
	@Test (priority = 7)
   public void DisplaySummary()
   {
	   String SubtotalA = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[1]/div[2]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("SubtotalA : "+SubtotalA);
	   
	   String DiscountEarned = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[1]/div[3]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("DiscountEarned : "+DiscountEarned);
	   
	   String Tax = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[2]/div[4]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("Tax : "+Tax);
	   
	   String OrderTotal  = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[2]/div[5]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("Order Total : "+OrderTotal);
	   
	   String ConsultantDiscApplied  = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[2]/div[6]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("Consultant Discount Applied : "+ConsultantDiscApplied);
	   
	   String CCpayments = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[2]/div[7]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("Credit Card Payments : "+CCpayments);
	   
	   String AmtDueNorwex = dr.findElement(By.xpath("html/body/div[2]/div/div[5]/div[6]/div[1]/div[2]/div[2]/div[8]/div[2]"))
			   .getText().replaceAll("[^0-9.]", "");
	   System.out.println("Amount Due Norwex : "+AmtDueNorwex);
	   
   }
	
/******************************************************************************************************
*********************************** Service Methods ***************************************************
*******************************************************************************************************/
	
	public void AddProduct(String item, int i) throws InterruptedException
	{
		getobject("SearchItem_input").clear();
		getobject("SearchItem_input").sendKeys(item);
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys(String.valueOf(i)); 
		this.Short(2);   
		getobject("Add_button").click();
		this.Short(2);
		
		//this.refresh();
	}
	
	public void EditQuantity(int i) throws InterruptedException
	{
		getobjectB("PIEdit_icon").click();
		this.Short(4);
		getobjectB("PIChangeQty_input").clear();
		getobjectB("PIChangeQty_input").sendKeys(String.valueOf(i));
		getobjectB("PIUpdate_button").click();
	}
	
	public void Remove() throws InterruptedException
	{
		getobjectB("PIDelete1_icon").click();
		this.Short(2);
		getobjectB("PIDelete2_button").click();
	}
	
}
