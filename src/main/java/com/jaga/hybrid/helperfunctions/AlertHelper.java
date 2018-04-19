package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;



/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 19/4/2018
 *
 */
public class AlertHelper {

	public static WebDriver driver;
	public static Logger logger = LoggerHelper.getLogger(AlertHelper.class);

	public static Alert getAlert() {
		logger.info("Getting the Alert");
		return driver.switchTo().alert();

	}

	public static void acceptAlert() {
		logger.info("Accepting the Alert");
		getAlert().accept();
	}

	public static void dismissAlert() {
		logger.info("Dismissing the Alert");
		getAlert().dismiss();
	}

	public static String getAlertText() {
		logger.info("Getting the text from the Alert");
		String text = getAlert().getText();
		return text;

	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			logger.info(true);
			return true;
		} catch (NoAlertPresentException exception) {
			logger.info(false);
			return false;
		}

	}

	public static void acceptAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		logger.info("Alert Present - Accepting the Alert");
		acceptAlert();
	}

	public static void dismissAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		logger.info("Alert Present - Dismissing the Alert");
		dismissAlert();
	}

	public static void acceptPrompt(String text) {
		if (!isAlertPresent()) {
			return;
		}
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		logger.info("Accepting the Prompt");
	}

}
