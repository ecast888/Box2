package com.norwex.employee;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.norwex.logins.Util;
import com.norwex.nco.TestBase;
public class ConsultantSearch extends TestBase 
{
	
	
	@BeforeTest
    public void Authenticate() throws IOException, InterruptedException 
	{   
		initialize();
		Util.neoLogin();
    }
	
	@Test
	public void SearchConsultant() throws InterruptedException
	{
		this.SearchById(1481294);
		//this.SearchByName("alexy", "desouza");
	}
	
	/**************************************************************************************************************
	*************************************** Service Methods *******************************************************
	**************************************************************************************************************/
	
	public void SearchById(int i ) throws InterruptedException
	{
		getemp("ConsultantID_input").clear();
		getemp("ConsultantID_input").sendKeys(String.valueOf(i));
		getemp("ConsultantID_input").click();
		this.Short(2);
		getemp("Search_button").click();
		this.Short(4);
		
		if(isElementPresent(By.id("neoFocusedConsultantId")))
		{
		System.out.println("!---- Search returned a consultant ----!");
		}
	else
		{  		
		System.out.println("!--- Something went wrong ---!");
		Assert.fail();
		}
	this.Short(2);
	
	}
	
	public void SearchByName(String fname, String lname ) throws InterruptedException
	{
		getemp("FirstName_input").sendKeys(fname);
		getemp("LastName_input").sendKeys(lname);
		getemp("Search_button").click();
	}
	
	public void ClearAll()
	{
		getemp("ConsultantID_input").clear();
		getemp("FirstName_input").clear();
		getemp("LastName_input").clear();
	}
}	