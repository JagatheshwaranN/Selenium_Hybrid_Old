package com.jaga.hybrid.helperfunctions;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jaga.hybrid.commonfunctions.BaseClass;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 20/4/2018
 *
 */
public class BrowserHelper extends BaseClass {

	public static Logger logger = LoggerHelper.getLogger(BrowserHelper.class);

	public static void goBack() {
		driver.navigate().back();
		logger.info("Browser Navigate to Back Page");
	}

	public static void goForward() {
		driver.navigate().forward();
		logger.info("Browser Navigate to Front Page");
	}

	public static void refresh() {
		driver.navigate().refresh();
		logger.info("Browser Refresh the Current Page");
	}

	public static Set<String> getWindowHandles() {
		logger.info("Capturing Windows Unique Alphanumeric ids");
		return driver.getWindowHandles();
	}

	public static void SwitchToWindow(int index) {

		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());

		if (index < 0 || index > windowsid.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsid.get(index));
		logger.info("Index of Windows : " + index);
	}

	public static void switchToParentWindow() {
		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsid.get(0));
		logger.info("Switch To Parent Window");
	}

	public static void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());

		for (int i = 1; i < windowsid.size(); i++) {
			logger.info("Child Window Id : " + windowsid.get(i));
			driver.switchTo().window(windowsid.get(i));
			driver.close();
		}
		switchToParentWindow();
	}

	public static void switchToFrame(String nameOrid) {
		driver.switchTo().frame(nameOrid);
		logger.info("Frame Name or Id : " + nameOrid);
	}

	public static String getCurrentPageUrl() {
		String url = null;
		url = driver.getCurrentUrl();
		logger.info("Current Page Url : " + url);
		return url;
	}

}
