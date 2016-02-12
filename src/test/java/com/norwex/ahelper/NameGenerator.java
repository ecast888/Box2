package com.norwex.ahelper;


import org.testng.annotations.Test;

public class NameGenerator {
	
	public static String Fname;
	public static String Lname;
	
  
     @Test (priority=2)   
     public void newnamer()
     {
    	 
    	 String[] Fnames = {"Sophia", "Emma", "Olivia", "Isabelle", "Mia","Zoe","Lily", "Emily","Aubrey","Abigail","Madelyn","Madison","Hailey","Kaitlyn","Natalie","Alyssa"};
    	 String[] Lnames = {"Smith", "Jackson", "Williams", "Brown", "Jones","Miller","Davis", "Garcia","Rodriguez","Wilson","Green","Martinez","Anderson","Lopez","Sanchez"};
    	 
         int index = (int) (Math.random() * Fnames.length);
         int index2 = (int) (Math.random() * Lnames.length);
         
         Fname = Fnames[index];
         System.out.println("Your First name is " + Fname + " now!");
         
         Lname = Lnames[index2];
         System.out.println("Your Last name is " + Lname + " now!");
         
     }       
}