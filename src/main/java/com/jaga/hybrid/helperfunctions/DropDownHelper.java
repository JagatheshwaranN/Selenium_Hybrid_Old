package com.jaga.hybrid.helperfunctions;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.jaga.hybrid.commonfunctions.BaseClass;

/**
 * 
 * @author Jagatheshwaran
 * @since 16/3/2018
 * @Modified 20/4/2018
 *
 */
public class DropDownHelper extends BaseClass{

	public static Logger logger = LoggerHelper.getLogger(DropDownHelper.class);

	public static void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		logger.info("Element : " + element + "Value : " + value);
	}

	public static void selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		logger.info("Element : " + element + "Index : " + index);
	}

	public static void selectByVisibleText(WebElement element, String visibletext) {
		Select select = new Select(element);
		select.selectByVisibleText(visibletext);
		logger.info("Element : " + element + "Visible Text : " + visibletext);
	}

	public static String getSelectValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		logger.info("Element : " + element + "Value : " + value);
		return value;

	}

	public static List<String> getAllDropDownValue(WebElement element) {
		Select select = new Select(element);
		List<WebElement> listelements = select.getOptions();
		List<String> dropdownvalues = new LinkedList<String>();
		for (WebElement elements : listelements) {
			logger.info(elements.getText());
			dropdownvalues.add(elements.getText());
		}
		return dropdownvalues;

	}

}
