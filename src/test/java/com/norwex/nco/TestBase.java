package com.norwex.nco;
import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import datatable.Xls_Reader;

public class TestBase 
{				
			//static DateFormat dateFormat = new SimpleDateFormat("yyMMDDHHmmss");  //yyMMDDHHmmss
			//static DateFormat dateFormat = new SimpleDateFormat("ddHHmmss");  	//yyMMDDHHmmss  
				static DateFormat dateFormat = new SimpleDateFormat("ddHHmm");  	 
			    static Date date = new Date();  
			    public static String rand = dateFormat.format(date);
			// Initializing the property file reading
				public static Properties CONFIG=null;
				public static ProfilesIni prof= new ProfilesIni();
				public static FirefoxProfile fp= prof.getProfile("Standard");
				public static Properties poe=null;
				public static Properties pcs=null;
				public static Properties pws=null;
				public static Properties emp=null;
				public static Properties enro=null;
				public static Properties con =null;
				public static WebDriver dr=null;
				public static WebDriverWait drive=null;
				public static EventFiringWebDriver driver=null;
				public static Xls_Reader datatable = null;
				public static Actions action=null;
				
				
		public void initialize() throws IOException{
			if (driver==null){
		//	config properties file
				CONFIG = new Properties();
				FileInputStream fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//config.properties");
				CONFIG.load(fn);
				
			/******************************************************************************************************
			********************************************* Object Configuration **************************************************
			*******************************************************************************************************/	
		//	party order properties
				poe = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//poe.properties");
				poe.load(fn);
		// Enrollment propeties 
				enro = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//enro.properties");
				enro.load(fn);
		//	PSC properties
				pcs = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//pcs.properties");
				pcs.load(fn);
				
		//	PWS properties
				pws = new Properties();
				
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//pws.properties");
				pws.load(fn);
				
		// EMP properties
				emp = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//emp.properties");
				emp.load(fn);
		// contacts properties
				con = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//config//contact.properties");
				con.load(fn);
				
				
				
			/******************************************************************************************************
			********************************************* Browsers ************************************************
			*******************************************************************************************************/
				if (CONFIG.getProperty("browser").equals("firefox")) {
				dr = new FirefoxDriver(fp);
				dr.manage().window().maximize();
				dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				
//				}else if (CONFIG.getProperty("browser").equals("chrome")) {
//					System.setProperty("webdriver.chrome.driver", "C://Wspace//chromedriver.exe");
//					 dr = new ChromeDriver();
//					 dr.manage().window().maximize(); 
				}
		// 	load the suite1 sheet
				datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//test//java//datatable//Controller.xlsx");
			 	driver= new EventFiringWebDriver(dr);
			 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
			
		}
		
		
		/******************************************************************************************************
		********************************************* Use Objects *********************************************
		*******************************************************************************************************/
		public static WebElement getcon(Object xpathKey){
			try{
				return dr.findElement(By.xpath(con.getProperty((String) xpathKey)));
				}catch(Throwable t){
				return null;
			}
		}
		public static WebElement getenro(Object xpathKey){
			try{
				return dr.findElement(By.xpath(enro.getProperty((String) xpathKey)));
				}catch(Throwable t){
				return null;
			}
		}
		
		public static WebElement getobject(Object xpathKey){
			try{
				return dr.findElement(By.xpath(poe.getProperty((String) xpathKey)));
				}catch(Throwable t){
				return null;
			}
		}
		public static WebElement getobjectB(Object xpathKey){
			try{
					return dr.findElement(By.xpath(pcs.getProperty((String) xpathKey)));
					}catch(Throwable t){
					return null;
				}
			}	
			
		public static WebElement getpws(Object xpathKey){
			try{
					return dr.findElement(By.xpath(pws.getProperty((String) xpathKey)));
					}catch(Throwable t){
					return null;
				}
			}	
			
		public static WebElement getemp(Object xpathKey){
			try{
					dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					return dr.findElement(By.xpath(emp.getProperty((String) xpathKey)));
					}catch(Throwable t){
					Assert.assertTrue(false, t.getMessage());
					return null;
				}
			}
			
		
		/******************************************************************************************************
		********************************************* Waits ***************************************************
		*******************************************************************************************************/

			public void Short(int i) throws InterruptedException
			{
				Thread.sleep(i*1000);
			}
			
		/******************************************************************************************************
		********************************************* Frequent Calls ******************************************
		*******************************************************************************************************/

			protected boolean TextIsPresent(String text){
			    try{
			        boolean b = driver.getPageSource().contains(text);
			        return b;
			    }
			    catch(Exception e){
			        return false;
			    }
			  }
			public boolean isElementsPresent(String xpathExpression)
			{
				List<WebElement> allElements= driver.findElements(By.xpath(xpathExpression));
				if(allElements.size()==0)
					return false;
				else
					return true;
			}
			public boolean isElementPresent(By by) {
			    try {
			      driver.findElement(by);
			      return true;
			    } catch (org.openqa.selenium.NoSuchElementException e) {
			      return false;
			    }
			}
 /*********************************** Browser control ***************************************************************/		
			public void refresh() throws InterruptedException
			{
				driver.navigate().refresh();    // refresh webpage
			}
			public void close(int s) throws InterruptedException
			{
				this.Short(s);           //close the browser
				dr.quit();
			}
			public void zoomout()
			{
				//To zoom In page 2 time using CTRL and + keys. 
				for(int i=0; i<2; i++)
				{ 
					driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				} 
			} 
			public void backtab() throws AWTException
			{
				Robot rob = new Robot();
				rob.keyPress(KeyEvent.VK_BACK_SPACE);
				rob.keyRelease(KeyEvent.VK_BACK_SPACE);             // hit backspace key
			}
			public void backspace()
			{
			 driver.navigate().back();       					   //hit enter key
			}
			public void enter()
			{
			 Actions action = new Actions(driver); 
			 action.sendKeys(Keys.ENTER).build().perform();        //hit enter key
			}
			public void tab()
			{
				 Actions action = new Actions(driver); 
				 action.sendKeys(Keys.TAB).build().perform();     //hit enter key
			}
			
		/******************************************************************************************************
		********************************************* Calculations *********************************************
		*******************************************************************************************************/
			
			 public void Twodecimal(double d ) 
			 	 {
			        int r = (int) Math.round(d*100);
			        double f = r / 100.0;
			        System.out.println(f);
			     }
		/******************************************************************************************************
		********************************************* Name Generator *********************************************
		*******************************************************************************************************/
					
}		

















