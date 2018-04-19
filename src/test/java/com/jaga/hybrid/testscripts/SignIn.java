package com.jaga.hybrid.testscripts;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;
import com.jaga.hybrid.helperfunctions.LoggerHelper;
import com.jaga.hybrid.helperfunctions.VerificationHelper;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 23/3/2018
 *
 */
public class SignIn extends BaseClass {
	
	public static Logger logger = LoggerHelper.getLogger(SignIn.class);

	@DataProvider(name = "testData")
	public String[][] dataSource() {
		return getData("testData.xlsx", "SignIn");

	}

	@Test(dataProvider = "testData")
	public void signIn(String email, String password) {
		try {
			logger.info("In Application Login Page");
			BaseClass.getWebElement("email").sendKeys(email);
			BaseClass.getWebElement("password").sendKeys(password);
			BaseClass.getWebElement("loginButton").click();
			VerificationHelper.isDisplayed(BaseClass.getWebElement("signInSucessMessage"));
			logger.info("Login to Application is Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
