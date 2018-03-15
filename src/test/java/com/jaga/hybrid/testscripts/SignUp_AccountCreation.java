package com.jaga.hybrid.testscripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;

public class SignUp_AccountCreation extends BaseClass{
	
	BaseClass bc;

	@DataProvider(name="testData")
	public String[][] dataSource()
	{
		return getData("testData.xlsx", "CreateAccount");
		
	}
	
	@BeforeMethod
	public void launchBrowser() throws IOException
	{
		init();
	}
	
	@Test(dataProvider="testData")
	public void accountRegistration(String Emailid,String Password)
	{
		System.out.println("Data in SignUp"+" " +Emailid+" "+Password);
	 /*driver.findElement(By.xpath("//a[@class='login']")).click();
	 driver.findElement(By.xpath("//form[@id='create-account_form']")).isDisplayed();
	 driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(emailid);
	 driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();*/
		
		
		 driver.findElement(By.name("email_address")).sendKeys(Emailid);
		 driver.findElement(By.name("password")).sendKeys(Password);
		 driver.findElement(By.id("tdb5")).click();
		
	
	}
	
	
	/*@AfterMethod
	public void closeBrowser() throws IOException
	{
		//driver.get("http://automationpractice.com/index.php");
		//bc.startBrowser("chrome");
		driver.close();
	}*/
	
	
	
	
	
	
	

}
