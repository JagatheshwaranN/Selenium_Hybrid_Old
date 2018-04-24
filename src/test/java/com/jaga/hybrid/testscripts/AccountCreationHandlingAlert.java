package com.jaga.hybrid.testscripts;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.hybrid.commonfunctions.BaseClass;
import com.jaga.hybrid.helperfunctions.AlertHelper;
import com.jaga.hybrid.helperfunctions.DropDownHelper;
import com.jaga.hybrid.helperfunctions.LoggerHelper;

public class AccountCreationHandlingAlert extends BaseClass {

	public static final Logger logger = LoggerHelper.getLogger(AccountCreationHandlingAlert.class);

	@DataProvider(name = "CAHandlingAlert")
	public String[][] createAccountHandlingAlert() {
		return getData("testData.xlsx", "CAHandlingAlert");

	}

	@Test(dataProvider = "CAHandlingAlert")
	public void createAccountHandlingAlert(String firstName, String lastName, String dob, String email, String company,
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
			BaseClass.getWebElement("confirmationPassword").sendKeys("");
			BaseClass.getWebElement("submitButton").click();
			AlertHelper.acceptAlertIfPresent();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
