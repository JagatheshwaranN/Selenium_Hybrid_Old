package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 *
 */
public class AlertHelper {

	public WebDriver driver;
	private static Logger logger =Logger.getLogger(AlertHelper.class.getName());

	// Created by Jagatheshwaran on 16/3/2018
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		logger.info("AlertHelper : " + this.driver.hashCode());
	}

	// Created by Jagatheshwaran on 16/3/2018
	public Alert getAlert() {
		logger.info("Getting the Alert");
		return driver.switchTo().alert();

	}

	// Created by Jagatheshwaran on 16/3/2018
	public void acceptAlert() {
		logger.info("Accepting the Alert");
		getAlert().accept();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void dismissAlert() {
		logger.info("Dismissing the Alert");
		getAlert().dismiss();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public String getAlertText() {
		logger.info("Getting the text from the Alert");
		String text = getAlert().getText();
		return text;

	}

	// Created by Jagatheshwaran on 16/3/2018
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			logger.info(true);
			return true;
		} catch (NoAlertPresentException exception) {
			logger.info(false);
			return false;
		}

	}

	// Created by Jagatheshwaran on 16/3/2018
	public void acceptAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		logger.info("Alert Present - Accepting the Alert");
		acceptAlert();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void dismissAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		logger.info("Alert Present - Dismissing the Alert");
		dismissAlert();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void acceptPrompt(String text) {
		if (!isAlertPresent()) {
			return;
		}
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		logger.info("Accepting the Prompt");
	}
	
}
