package com.jaga.hybrid.testscripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;
import com.jaga.hybrid.helperfunctions.VerificationHelper;

/**
 * 
 * @author Jagatheshwaran
 * @since /3/2018
 * @Modified - 23/03/2018
 *
 */
public class SignIn extends BaseClass {

	VerificationHelper verificationhelper = new VerificationHelper();

	@DataProvider(name = "testData")
	public String[][] dataSource() {
		return getData("testData.xlsx", "SignIn");

	}

	@Test(dataProvider = "testData")
	public void signIn(String email, String password) {
		try {
			BaseClass.getWebElement("email").sendKeys(email);
			BaseClass.getWebElement("password").sendKeys(password);
			BaseClass.getWebElement("loginButton").click();
			verificationhelper.isDisplayed(BaseClass.getWebElement("signInSucessMessage"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
