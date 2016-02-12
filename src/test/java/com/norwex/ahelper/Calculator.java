package com.norwex.ahelper;

import org.testng.annotations.Test;
import java.text.DecimalFormat;

public class Calculator  {
	
	 @Test
	 public void print()
	 {
		 
		 this.Twodecimal(3.9179);
		 this.Twodecimal(3.71789);
		 this.StringToInt("1234");
	 }
	 
/*************************************************************************************************************/ 
	public  void Twodecimal(double i) 
	{
		   DecimalFormat twoDForm = new DecimalFormat("#.00");
		    //System.out.println(twoDForm.format(i));
		        
		   String value=    twoDForm.format(i);
		   System.out.println("Converted to two decimal: "+value);
	}
	public void StringToInt(String str)
	{
			Integer x = Integer.valueOf(str);
			// or
		    int y = Integer.parseInt(str);
		    System.out.println("Int Value of input: "+y); 
	}
	
							}

