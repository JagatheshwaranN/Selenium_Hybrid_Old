package com.jaga.hybrid.helperfunctions;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 *
 */
public class BrowserHelper {

	public WebDriver driver;
	private static Logger logger = Logger.getLogger(BrowserHelper.class.getName());

	// Created by Jagatheshwaran on 16/3/2018
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		logger.info("BrowserHelper : " + this.driver.hashCode());
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void goBack() {
		driver.navigate().back();
		logger.info("Browser Navigate to Back Page");
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void goForward() {
		driver.navigate().forward();
		logger.info("Browser Navigate to Front Page");
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void refresh() {
		driver.navigate().refresh();
		logger.info("Browser Refresh the Current Page");
	}

	// Created by Jagatheshwaran on 8/3/2018
	public Set<String> getWindowHandles() {
		logger.info("Capturing Windows Unique Alphanumeric ids");
		return driver.getWindowHandles();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void SwitchToWindow(int index) {

		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());

		if (index < 0 || index > windowsid.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsid.get(index));
		logger.info("Index of Windows : " + index);
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void switchToParentWindow() {
		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsid.get(0));
		logger.info("Switch To Parent Window");
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());

		for (int i = 1; i < windowsid.size(); i++) {
			logger.info("Child Window Id : " + windowsid.get(i));
			driver.switchTo().window(windowsid.get(i));
			driver.close();
		}
		switchToParentWindow();
	}

	// Created by Jagatheshwaran on 16/3/2018
	public void switchToFrame(String nameOrid) {
		driver.switchTo().frame(nameOrid);
		logger.info("Frame Name or Id : " + nameOrid);
	}

}
