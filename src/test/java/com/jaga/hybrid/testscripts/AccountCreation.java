package com.jaga.hybrid.testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;
import com.jaga.hybrid.helperfunctions.BrowserHelper;
import com.jaga.hybrid.helperfunctions.DropDownHelper;
import com.jaga.hybrid.helperfunctions.LoggerHelper;
import com.jaga.hybrid.helperfunctions.VerificationHelper;

/**
 * 
 * @author Jagatheshwaran
 * @since 21/3/2018
 * @Modified 20/4/2018
 *
 */
public class AccountCreation extends BaseClass {

	public static final Logger logger = LoggerHelper.getLogger(AccountCreation.class);

	@DataProvider(name = "createAccount")
	public String[][] createAccount() {
		return getData("testData.xlsx", "CreateAccount");

	}

	@Test(dataProvider = "createAccount")
	public void createAccount(String firstName, String lastName, String dob, String email, String company,
			String address, String postcode, String city, String state, String Country, String phone, String password,
			String confirmPassword) {
		try {

			BaseClass.getWebElement("CreateAccContinueBtn").click();
			BaseClass.getWebElement("genderOption").click();
			BaseClass.getWebElement("firstName").sendKeys(firstName);
			BaseClass.getWebElement("lastName").sendKeys(lastName);
			BaseClass.getWebElement("dateOfBirth").sendKeys(dob);
			BaseClass.getWebElement("emailAddress").sendKeys(email);
			BaseClass.getWebElement("companyName").sendKeys(company);
			BaseClass.getWebElement("address").sendKeys(address);
			BaseClass.getWebElement("postCode").sendKeys(postcode);
			BaseClass.getWebElement("cityName").sendKeys(city);
			BaseClass.getWebElement("stateName").sendKeys(state);
			DropDownHelper.selectByVisibleText(BaseClass.getWebElement("countryName"), Country);
			BaseClass.getWebElement("telePhoneNumber").sendKeys(phone);
			BaseClass.getWebElement("password").sendKeys(password);
			BaseClass.getWebElement("confirmationPassword").sendKeys(confirmPassword);
			BaseClass.getWebElement("submitButton").click();

			String ExpectedUrl = "http://www.gcrit.com/build3/create_account_success.php";
			String ActualUrl = BrowserHelper.getCurrentPageUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				VerificationHelper.isDisplayed(BaseClass.getWebElement("successMessage"));
				logger.info("The Account Registration is Successful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			} else {
				VerificationHelper.isDisplayed(BaseClass.getWebElement("errorMessage"));
				logger.info("The Account Registration is UnSuccessful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
