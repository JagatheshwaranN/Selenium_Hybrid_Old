package com.jaga.hybrid.helperfunctions;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Jagatheshwaran
 * @since 19/4/2018
 *
 */
public class LoggerHelper {

	private static boolean root = false;

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class getclass) {
		if (root) {
			return Logger.getLogger(getclass);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/LogPropertyFile/log4j.properties");
		
		root = true;
		return Logger.getLogger(getclass);

	}

	public static void main(String ar[])
	{
		System.out.println(System.getProperty("user.dir")+"/src/main/resources/LogPropertyFile/log4j.properties");
		
	}
}
