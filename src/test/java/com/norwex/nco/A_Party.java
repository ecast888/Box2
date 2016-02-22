package com.norwex.nco;
import com.norwex.logins.*;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


	public class A_Party extends TestBase
	{
	    private static String addressInput= "851 Greenside Dr", addressInput2 ="Apt 1204";
	    private static String zipcodeInput="75080";
	    private static String cellPhone = "5155551155", cellPhone2= "8131154112";
	    private static String partyName = "Feb"+rand; 
	    public static  String newpartyName ="Feb"+rand;
	    	  
		Menus m = new Menus();
		
		
		
	@BeforeTest
    public void Authenticate() throws IOException, InterruptedException
    {  
		initialize();
		Util.ncoLogin();
    }
	/******************************************************************************************************
	*********************************** Create new party **************************************************
	*******************************************************************************************************/
	
	@Test (priority=1)
	public void NavigateToPartyPage() throws InterruptedException
	{
		m.partyorder();
	}
	
	@Test (priority=2)
	public void createParty () throws InterruptedException, ClassNotFoundException
	{   
		System.out.println("!--- Enter party details ---!");
		getobject("partyname_input").sendKeys(partyName);
		this.Short(2);  
		getobject("partydate_input").click();
		this.enter();   
		getobject("hostfirstname_input").sendKeys("Elvis");
		getobject("hostlastname_input").sendKeys("McNeely");
		getobject("hostemail_input").sendKeys("Laund"+rand+"@norwex.com");
		getobject("hostmobile_input").sendKeys(cellPhone);
		getobject("hostmobile2_input").sendKeys(cellPhone2);
		getobject("hostadderess_input").sendKeys(addressInput);
		getobject("hostadderess2_input").sendKeys(addressInput2);
		getobject("zipcode_input").sendKeys(zipcodeInput);
		this.Short(1);
		getobject("zipcode_search_button").click();
		this.Short(2);   
		getobject("AddressValidateAlert").click();   // OR1 *-> create party
		this.Short(2);
		getobject("startpartynow").click();
	}
	
	/******************************************************************************************************
	*********************************** Confirm default shipping ******************************************
	*******************************************************************************************************/
	
	@Test (priority=3, dependsOnMethods={"createParty"})
	public void ConfirmShipping() throws ClassNotFoundException, InterruptedException
	{   
		this.Short(2);  
		getobject("Continue1_button").click();
	} 
	
	/******************************************************************************************************
	*********************************** Add Guests ********************************************************
	*******************************************************************************************************/
	
	@Test (priority=4, dependsOnMethods={"createParty"})
	public void addGuests() throws ClassNotFoundException, InterruptedException
	{   
		this.addGuest("Mia", "Jones", "MJ"+rand+"@norwex.com");   	// 1st guest
		this.addGuest("Aubrey", "Wilson", "AW"+rand+"@norwex.com");	// 2nd guest
		this.addGuest("Zoe", "Martinez", "ZM"+rand+"@norwex.com");  // 3rd guest
	} 
	/******************************************************************************************************
	*********************************** Add Guest orders **************************************************
	*******************************************************************************************************/
	
	@Test (priority=5, dependsOnMethods={"addGuests"})
	public void addGuestOrders() throws InterruptedException, ClassNotFoundException
	{
		
	//refresh();
	
	this.addMultipleItems("Cart3_button", "354100 - Spirinetts - $5.49",
			                              "354102 - Spirisponge - $9.99",
										  "356400 - Mop Brackets - $11.99", 1);
	this.PayWithCash();
	
	this.addMultipleItems("Cart2_button", "1120 - Dryer Balls - $20",
										  "354000 - Spray Bottle - $6.99",
								          "1505 - Basic Package - $32.99", 1);
	this.PayWithCredit();

	this.addMultipleItems("Cart1_button", "1128 - Sportzyme - $20.99",
										  "1403 - Out to Lunch Duo - $17.99",
										  "354000 - Spray Bottle - $6.99",  1);
	this.PayWithCheck();
	
	}
	
	/******************************************************************************************************
	*********************************** Update Guest orders ***********************************************
	*******************************************************************************************************/
	
	@Test (priority=6, dependsOnMethods={"addGuestOrders"})
	public void UpdateGuestOrders() throws InterruptedException
	{
		this.UpdateItem("2");
		this.Short(2);
		this.RemoveItem();  
	}
	
	/******************************************************************************************************
	*********************************** Add Host orders****************************************************
	*******************************************************************************************************/
	
	@Test (priority=7, dependsOnMethods={"addGuestOrders"})
	public void addHostOrder() throws InterruptedException
	{  
		System.out.println("!--- Starting host order ---!");
		getobject("AddHostOrder_link").click();
		this.AddHostOrder("356400 - Mop Brackets - $11.99", 1);
		this.AddHostOrder("357053 - Bottle Brush Sleeve - $5.99", 1);
		this.AddHostOrder("357010 - Rubber Brush - $14.99", 3);
		this.Short(2);
	}
	
	/************************************************************************************************************************
	*********************************** Review Bookings or Add new Booking***************************************************
	*************************************************************************************************************************/
	
	@Test (priority=8, dependsOnMethods={"addHostOrder"})
	public void ReviewBookings () throws InterruptedException
	{  
		System.out.println("!---Review Bookings---!");
		getobject("ReviewBookings_link").click();
		this.Short(2);
		//getobject("AcceptHost_alert").click();
		this.NewBooking(newpartyName);
		this.Short(3);
		getobject("Continue_button").click();   
	}

	/******************************************************************************************************
	*********************************** Pay for Orders ****************************************************
	*******************************************************************************************************/
	@Test (priority=9, dependsOnMethods={"ReviewBookings"})
	public void FinalPayment() throws InterruptedException
	{  
		System.out.println("!---Confirm Final Pay---!");
		this.Short(2);
		getobject("CardholderName").clear();
		getobject("CardholderName").sendKeys("Alexy Desouza");
		getobject("Add_button2").click();
		getobject("ReviewParty").click();
		this.Short(2);
		getobject("Finish_button").click();
	}
	
	@Test (priority = 10, dependsOnMethods={"ReviewBookings"})
	   public void HostPlanSummary()
	   {
		  System.out.println("!------ Host Plan Summary -------!");
		  String GuestOrderVolume= dr.findElement(By.xpath("//*[@id='summaryToggle']/div[2]/div[1]/div[2]/div[1]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("GuestOrderVolume : "+GuestOrderVolume);

		   
		   String Attending = dr.findElement(By.xpath("//*[@id='summaryToggle']/div[2]/div[1]/div[2]/div[2]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" Attending : "+Attending);
		   
		   String NonAttending  = dr.findElement(By.xpath(".//*[@id='summaryToggle']/div[2]/div[1]/div[2]/div[3]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" NonAttending : "+NonAttending);
		   
		   String HostDiscountEarned = dr.findElement(By.xpath("//*[@id='summaryToggle']/div[2]/div[3]/div[2]/div[5]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" HostDiscountEarned: "+HostDiscountEarned);
		   
		   String HostDiscountApplied = dr.findElement(By.xpath("//*[@id='summaryToggle']/div[2]/div[3]/div[2]/div[6]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" HostDiscountApplied : "+HostDiscountApplied);
		   
		   String HostDiscountRemaining = dr.findElement(By.xpath("//*[@id='summaryToggle']/div[2]/div[3]/div[2]/div[7]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" HostDiscountRemaining : "+HostDiscountRemaining);
		   
		   String HostOverage = dr.findElement(By.xpath("//*[@id='summaryToggle']/div[3]/div[3]/div[2]/div[4]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println(" Host Overage : "+HostOverage);
		   
		   
		 //++++++++++++++++++++++++++++++++++++ Attending Guest Discount and Payment Summary ++++++++++++++++++++++++++++++++++++++++++ 
		 
		   
		   System.out.println("!----- Consultant (Attending Guest Discount) and Payment Summary -----!");
		   String SubtotalA = dr.findElement(By.xpath("//*[@id='discountToggle']/div[1]/div[2]/div/div[2]/div/div[1]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("SubtotalA : "+SubtotalA);
		   
		   String DiscountEarned = dr.findElement(By.xpath("//*[@id='discountToggle']/div[1]/div[2]/div/div[2]/div/div[3]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Cons Discount Percentage : "+DiscountEarned);
		   
		   String DiscountApplied  = dr.findElement(By.xpath("//*[@id='discountToggle']/div[1]/div[2]/div/div[2]/div/div[4]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Cons Discount Earned: "+DiscountApplied);
		   
		   String PartyOrderTotal  = dr.findElement(By.xpath("//*[@id='discountToggle']/div[2]/div[2]/div/div/div[2]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Cons Discount Applied : "+PartyOrderTotal);
		   
		   String CreditCardPayments = dr.findElement(By.xpath("//*[@id='discountToggle']/div[2]/div[2]/div/div/div[3]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Cons Discount Posted : "+CreditCardPayments);
		   
		   String AccountBalanceApplied = dr.findElement(By.xpath("//*[@id='discountToggle']/div[2]/div[2]/div/div/div[5]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Party Item Total : "+AccountBalanceApplied);
		   
		   String AmtDueNorwex = dr.findElement(By.xpath("//*[@id='discountToggle']/div[2]/div[2]/div/div/div[6]/div[2]"))
				   .getText().replaceAll("[^0-9.]", "");
		   System.out.println("Amount Due Norwex: "+AmtDueNorwex);
		   
	   }
	
	@Test (priority = 10, dependsOnMethods={"HostPlanSummary"})
	   public void VerifySummary()
	   {
		
	   }
//	@AfterTest
//	public void closebrowser() throws InterruptedException
//		{
//			this.close(15);
//		}
	
	/**************************************************************************************************************
	***************************************************************************************************************
	*************************************** Service Methods *******************************************************
	***************************************************************************************************************
	**************************************************************************************************************/
	
	private void addGuest(String firstname, String lastName, String email) throws InterruptedException
	{
		System.out.println("!---- Adding guest information ----!");
		getobject("AddNewGuest_button").click();
		getobject("GuestFirstName_input").clear();
		getobject("GuestFirstName_input").sendKeys(firstname); //from custom list *Name generator
		getobject("GuestLastName_input").clear();
		getobject("GuestLastName_input").sendKeys(lastName);  //from custom list *Name generator
		getobject("GuestEmail_input").sendKeys(email);       /*TimeStamped unique email to avoid duplicates*/
		getobject("GuestPhone_input").clear();
		getobject("GuestPhone_input").sendKeys(cellPhone);
		getobject("GuestPhone2_input").clear();
		getobject("GuestPhone2_input").sendKeys(cellPhone2);
		getobject("GuestAddress_input").clear();
		getobject("GuestAddress_input").sendKeys(addressInput);
		getobject("GuestAddress2_input").clear();
		getobject("GuestAddress2_input").sendKeys(addressInput2);
		getobject("GuestZipcode_input").clear();
		getobject("GuestZipcode_input").sendKeys(zipcodeInput);
		getobject("GuestZipcode_Search").click();
		this.Short(3);
		getobject("AddressValidateAlert").click();
		this.Short(2);
		getobject("SaveGuest").click();
	}
	
	private void addSingleItem(String cart, String item, int i) throws InterruptedException
	
		{
		System.out.println("!--- Adding single order --!");
		getobject(cart).click();   // cart 4 corresponds with guest 1
		this.Short(2);
		getobject("SearchItem_input").clear();
		getobject("SearchItem_input").sendKeys(item);
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys("2");
		this.Short(2);
		getobject("Add_button").click();
		
		}
	
	private void addMultipleItems(String cart, String item1, String item2, String item3, int i) throws InterruptedException
		{
		System.out.println("!--- Adding multiple guest orders ---!");
		this.Short(2);
		getobject(cart).click();   // cart 4 corresponds with guest 1
		getobject("SearchItem_input").clear();
		getobject("SearchItem_input").sendKeys(item1);    // item1
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys(String.valueOf(i));
		this.Short(2);
		
		getobject("Add_button").click();
		this.Short(2);
		
		getobject("SearchItem_input").sendKeys(item2);    //item 2
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys(String.valueOf(i));
		this.Short(2);
		getobject("Add_button").click();
		this.Short(2);
		
		getobject("SearchItem_input").sendKeys(item3);   //item 3
		getobject("Quantity_input").clear();
		getobject("Quantity_input").sendKeys(String.valueOf(i));
		this.Short(2);
		getobject("Add_button").click();
		this.Short(2);
		}
	
	private void UpdateItem(String i) throws InterruptedException
	{
		System.out.println("!--- Update Item quantity ---!");
		getobject("EditQty_icon").click();
		this.Short(1);
		getobject("ChangeQtyAlert_input").clear();
		getobject("ChangeQtyAlert_input").sendKeys(String.valueOf(i));
		this.Short(1);
		getobject("UpdateQtyAlert_button").click();
	}
	
	private void RemoveItem() throws InterruptedException
	{
		System.out.println("!--- Delete guest order ---!");
	    this.Short(2);
		getobject("DeleteGuestOrder_icon").click();
		getobject("DeleteItem_button").click();
	}
	
	/*------------------------------------ Payments -----------------------------------------------------*/
	
	
	private void PayWithCredit() throws InterruptedException
	{
		
		this.Short(3);
		Select con = new Select(driver.findElement(By.id("norwex_maxbundle_orderpaymentmethod_paymentmethod")));
		con.selectByVisibleText("Credit Card"); 
		this.Short(2);
		getobject("Add_button2").click();
		
//		driver.findElement(By.xpath("//*[text()='select']")).click();
//		driver.findElement(By.xpath("//*[text()='Credit Card']")).click();
//		getobject("Add_button2").click();
		
	}
	
	private void PayWithCash() throws InterruptedException    // Element is hidden *Use JavaScript Executor
	{
		//Select con = new Select(getobject("PaymentMethod_dropdown2"));
		Select con = new Select(driver.findElement(By.id("norwex_maxbundle_orderpaymentmethod_paymentmethod")));
		con.selectByVisibleText("Cash"); 
		
		String AmountDisplayed= dr.findElement(By.id("norwex_maxbundle_payment_amountcharged")).getText();
		System.out.println("Amount Displayed: "+ AmountDisplayed);
		
		WebElement Addbutton =dr.findElement(By.id("norwex_maxbundle_payment_submit"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
		
	}
	
	private void PayWithCheck() throws InterruptedException
	{
		Select con = new Select(driver.findElement(By.id("norwex_maxbundle_orderpaymentmethod_paymentmethod")));
		con.selectByVisibleText("Check"); 
		driver.findElement(By.id("norwex_maxbundle_payment_checknumber")).sendKeys("115002");
		
		WebElement Addbutton =dr.findElement(By.id("norwex_maxbundle_payment_submit"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Addbutton);
	}
	
	
	private void AddHostOrder(String item, int i) throws InterruptedException
	{
		System.out.println("!--- Starting host order ---!"); //
		getobject("HostItem_input").clear();
		getobject("HostItem_input").sendKeys(item);
//		System.out.println("visible? :"+dr.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']")).getText());
//		this.Short(1);
//		getobject("GuestItem_Ajax").click();
		getobject("HostQty_input").clear();
		getobject("HostQty_input").sendKeys(String.valueOf(i));
		this.Short(2);
		getobject("Add_button").click();
	}

	private void NewBooking (String newpartyName) throws InterruptedException
	{  
		System.out.println("!--- Review Bookings ---!");
		this.Short(2);
		getobject("AddNewBooking_button").click();
		getobject("partyname_input").sendKeys(newpartyName);
		getobject("partydate_input").click();
		this.Short(1);
		this.enter();
		this.Short(2);
		getobject("Save").click();
	}
	
}	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	