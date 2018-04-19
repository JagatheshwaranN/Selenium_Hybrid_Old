package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 19/4/2018
 *
 */
public class JavaScriptHelper {

	public static WebDriver driver;
	public static Logger logger = LoggerHelper.getLogger(JavaScriptHelper.class);

	public static Object executeScript(String script) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		logger.info(script);
		return executor.executeAsyncScript(script);
	}

	public static Object executeScript(String script, Object... arguments) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		logger.info(script);
		return executor.executeAsyncScript(script, arguments);
	}

	public static void scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		logger.info(element);
	}

	public static void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		logger.info(element);
	}

	public static void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		logger.info(element);
	}

	public static void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		logger.info(element);
	}

	public static void scrollUpVertical() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public static void scrollDownVertical() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void ScrolUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public static void ScrolDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public static void zoomInByPercentage() {
		executeScript("document.body.style.zoom='50'");
	}
}