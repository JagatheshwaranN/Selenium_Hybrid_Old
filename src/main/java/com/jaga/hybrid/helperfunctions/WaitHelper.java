package com.jaga.hybrid.helperfunctions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jaga.hybrid.commonfunctions.BaseClass;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 20/4/2018
 *
 */
public class WaitHelper extends BaseClass {

	public static WebDriverWait wait;
	public static Logger logger = LoggerHelper.getLogger(WaitHelper.class);

	public static void setImplicitWait(long timeout, TimeUnit unit) {
		logger.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public static WebDriverWait getPollingWait(int timeout, int pollingsec) {
		wait = new WebDriverWait(driver, timeout);
		wait.pollingEvery(pollingsec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public static void waitForElementVisible(WebElement element, int timeout, int pollingsec) {
		logger.info("Web Element : " + element);
		wait = getPollingWait(timeout, pollingsec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementClick(WebElement element, int timeout, int pollingsec) {
		logger.info("Web Element : " + element);
		wait = getPollingWait(timeout, pollingsec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
