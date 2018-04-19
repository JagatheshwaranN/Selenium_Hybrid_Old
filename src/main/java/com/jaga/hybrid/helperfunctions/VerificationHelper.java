package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 19/4/2018
 *
 */
public class VerificationHelper {

	public static Logger logger = LoggerHelper.getLogger(VerificationHelper.class);

	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
		} catch (Exception exception) {
			logger.error("Element not found :" + exception);
		}
		return isDisplayed;

	}

	public static synchronized boolean verifyTextEquals(WebElement element, String text) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(text)) {
				return flag = true;
			} else {
				return flag;
			}

		} catch (Exception exception) {
			logger.info("Element not found :" + element);
			return flag;
		}
	}

	public static String readTextValueFromElement(WebElement element) {

		if (element == null) {
			logger.info("WebElement is Null...");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

		if (!displayed)
			return null;
		String text = element.getText();
		logger.info("WebElement Text Value is : " + text);
		return text;
	}

	public static String readValueFromInput(WebElement element) {
		if (element == null)
			return null;
		if (!isDisplayed(element))
			return null;
		String value = element.getAttribute("value");
		logger.info("WebElement Value is : " + value);
		return value;
	}

	public static boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			logger.info("WebElement is displayed : " + element);
			return true;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}

	protected static boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			logger.info("WebElement is displayed : " + element);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return true;
		}
	}

	protected static String getDisplayText(WebElement element) {
		if (element == null)
			return null;
		if (!isDisplayed(element))
			return null;
		logger.info("WebElement Text Value is : " + element.getText());
		return element.getText();
	}

}
