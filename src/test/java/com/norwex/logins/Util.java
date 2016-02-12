package com.norwex.logins;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class Util extends com.norwex.nco.TestBase 
{
	
	/******************************************************************************************************
	********************************************* NCO *****************************************************
	*******************************************************************************************************/
	
	public static void ncoLogin () throws InterruptedException
	{   
		dr.get(CONFIG.getProperty("NcoLoginPage"));
		getobject("consultantid").sendKeys(CONFIG.getProperty ("ncousername"));
		getobject("password").sendKeys(CONFIG.getProperty ("ncopassword"));
		getobject("login").click();
	}
	
	
	/******************************************************************************************************
	*********************************************NEO*******************************************************
	*******************************************************************************************************/
	
	public static void neoLogin () throws InterruptedException
	{
		dr.get(CONFIG.getProperty("NeoLoginPage"));
		//dr.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));  // *zoom out
		getemp("norwexusername").sendKeys(CONFIG.getProperty ("neousername"));
		getemp("norwexpassword").sendKeys(CONFIG.getProperty ("neopassword"));
		getemp("login").click();
	}
	
	
	/******************************************************************************************************
	********************************************* PWS *****************************************************
	*******************************************************************************************************/
	
	public static void pwsLogin() throws InterruptedException
	{
		dr.get(CONFIG.getProperty("PwsLoginPage"));
		dr.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		driver.findElement(By.xpath("//*[@id='consultant_logout']")).click();
		getpws("email").sendKeys(CONFIG.getProperty ("pwsusername"));
		getpws("password").sendKeys(CONFIG.getProperty ("pwspassword"));
		getpws("login").click();
	}
	
	


	/******************************************************************************************************
	********************************************* REPORTS *****************************************************
	*******************************************************************************************************/
	public static void zip(String filepath){
		 try
		 	{
		 		File inFolder=new File(filepath);
		 		File outFolder=new File("Reports.zip");
		 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
		 		BufferedInputStream in = null;
		 		byte[] data  = new byte[1000];
		 		String files[] = inFolder.list();
		 		for (int i=0; i<files.length; i++)
		 		{
		 			in = new BufferedInputStream(new FileInputStream
		 			(inFolder.getPath() + "/" + files[i]), 1000);  
		 			out.putNextEntry(new ZipEntry(files[i])); 
		 			int count;
		 			while((count = in.read(data,0,1000)) != -1)
		 			{
		 				out.write(data, 0, count);
		 			}
		 			out.closeEntry();
	  }
	  out.flush();
	  out.close();
	}
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  } 
	 }
	/******************************************************************************************************
	********************************************* SCREENSHOTS *****************************************************
	*******************************************************************************************************/
	
	
//	public static void takeScreenShot(String fileName)
//		{
//			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		    try {
//				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		}	   
//		}	

}









