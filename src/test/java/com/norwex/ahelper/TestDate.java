package com.norwex.ahelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.testng.annotations.Test;

public class TestDate {

		//initialize random naming
		//static DateFormat dateFormat = new SimpleDateFormat("yyMMHHmmss");  //yyMMDDHHmmss
		static DateFormat dateFormat = new SimpleDateFormat("yy:MM:dd");   
	    static Date date = new Date();  
	    public static String rand = dateFormat.format(date);
	    
	 @Test   
	public void testdate()
	{
    System.out.println("print date: "+rand);
	}
	@Test   
	public void testzone()
		{
		    TimeZone zone = TimeZone.getDefault();
		    System.out.println(zone.getDisplayName());
		    System.out.println(zone.getID());
		    }
		}

