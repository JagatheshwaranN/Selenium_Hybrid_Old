package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.jaga.hybrid.commonfunctions.BaseClass;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 24/4/2018
 *
 */
public class AlertHelper extends BaseClass {

	public static Logger logger = LoggerHelper.getLogger(AlertHelper.class);

	public static Alert getAlert() {
		return driver.switchTo().alert();

	}

	public static void acceptAlert() {
		getAlert().accept();
		logger.info("Accepting the Alert");
	}

	public static void dismissAlert() {
		getAlert().dismiss();
		logger.info("Dismissing the Alert");
	}

	public static String getAlertText() {
		logger.info("Getting the text from the Alert");
		String text = getAlert().getText();
		logger.info("The Alert Text is : " + text);
		return text;

	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			logger.info("Alert is Present : " + true);
			return true;
		} catch (NoAlertPresentException exception) {
			logger.info("Alert is Not Present : " + false);
			return false;
		}

	}

	public static void acceptAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		logger.info("Alert Present - Accepting the Alert");
		getAlertText();
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
