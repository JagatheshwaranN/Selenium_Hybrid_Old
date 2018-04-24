package com.jaga.hybrid.testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;
import com.jaga.hybrid.helperfunctions.BrowserHelper;
import com.jaga.hybrid.helperfunctions.LoggerHelper;
import com.jaga.hybrid.helperfunctions.VerificationHelper;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 23/4/2018
 *
 */
public class SignIn extends BaseClass {

	public static Logger logger = LoggerHelper.getLogger(SignIn.class);

	String ExpectedUrl = BaseClass.getTestData("signInSucessUrl");

	@DataProvider(name = "signIn")
	public String[][] dataSource() {
		return getData("testData.xlsx", "SignIn");

	}

	@Test(dataProvider = "signIn")
	public void signIn(String email, String password) {
		try {
			logger.info("In Application Login Page");
			BaseClass.getWebElement("email").sendKeys(email);
			BaseClass.getWebElement("password").sendKeys(password);
			BaseClass.getWebElement("loginButton").click();

			String ActualUrl = BrowserHelper.getCurrentPageUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				VerificationHelper.isDisplayed(BaseClass.getWebElement("signInSucessMessage"));
				logger.info("The SignIn is Successful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			} else {
				VerificationHelper.isDisplayed(BaseClass.getWebElement("signInErrorMessage"));
				logger.info("The SignIn is UnSuccessful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
