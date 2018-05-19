package com.jaga.hybrid.commonfunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jaga.hybrid.excelreader.ExcelReader;
import com.jaga.hybrid.helperfunctions.LoggerHelper;
import com.jaga.hybrid.helperfunctions.WaitHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jagatheshwaran
 * @since 14/3/2018
 * @Modified 23/4/2018
 *
 */
public class BaseClass {

	public static final Logger logger = LoggerHelper.getLogger(BaseClass.class);

	public static WebDriver driver;
	public static File file;
	public static FileInputStream fileinputstream;
	public static Properties properties = new Properties();
	public static WebDriverWait wait;
	public static ExtentReports extentreports;
	public static ExtentTest extenttest;
	public static ITestResult result;
	public ExcelReader excelreader;

	//public static String extentReportPath = "./extent-report/";
	public static String extentReportPath = "./target/surefire-reports/";
	public static String browserDriverPath = "//src//main//resources//Drivers//";
	public static String propertyFilePath = "//src//main//java//com//jaga//hybrid//properties//object.properties";
	public static String excelFilePath = "//src//test//resources//TestData//";
	public static String screenShotPath = "//src//test//resources//Screenshots//";

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		//extentreports = new ExtentReports(extentReportPath + simpledateformat.format(calendar.getTime()) + ".html",
		extentreports = new ExtentReports(extentReportPath + "ExtentReport.html",
				true);
	}

	@BeforeMethod
	public static void beforeMethod(Method result) throws IOException {
		init();
		extenttest = extentreports.startTest(result.getName());
		extenttest.log(LogStatus.INFO, result.getName() + " Test Started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		getResult(result);
		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		extentreports.endTest(extenttest);
		extentreports.flush();
	}

	public static void init() throws IOException {
		loadProperties();
		logger.info("Operating Environment : " + System.getProperty("os.name"));
		logger.info("Browser : " + properties.getProperty("browser"));
		startBrowser(properties.getProperty("browser"));
		driver.get(properties.getProperty("baseUrl"));
		WaitHelper.setImplicitWait(20, TimeUnit.SECONDS);

	}

	public static void loadProperties() throws IOException {
		properties = new Properties();
		file = new File(System.getProperty("user.dir") + propertyFilePath);
		fileinputstream = new FileInputStream(file);
		properties.load(fileinputstream);

	}

	public static void startBrowser(String browser) {
		if (System.getProperty("os.name").contains("Windows")) {
			if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + browserDriverPath + "chromedriver.exe");
				driver = new ChromeDriver();
			} else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + browserDriverPath + "firefox.exe");
				driver = new FirefoxDriver();
			} else if (properties.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + browserDriverPath + "ie.exe");
				driver = new InternetExplorerDriver();
			}

		} else if (System.getProperty("os.name").contains("Mac")) {
			if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + browserDriverPath + "chromedriver");
				driver = new ChromeDriver();
			} else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + browserDriverPath + "firefox");
				driver = new FirefoxDriver();
			} else if (properties.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + browserDriverPath + "ie");
				driver = new InternetExplorerDriver();
			}
		}
	}

	public static void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extenttest.log(LogStatus.PASS, result.getName() + " Test is Passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			extenttest.log(LogStatus.SKIP, result.getName() + " Test is Skipped :-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extenttest.log(LogStatus.FAIL, result.getName(), " Test is Failed" + result.getThrowable());
			String screen = getSnapShot(result.getName());
			extenttest.log(LogStatus.FAIL, extenttest.addScreenCapture(screen));

		} else if (result.getStatus() == ITestResult.STARTED) {
			extenttest.log(LogStatus.INFO, result.getName() + " Test is Started");
		}
	}

	public static String getSnapShot(String imageName) throws IOException {
		if (imageName.equals("")) {
			imageName = "blank";
		}
		File sourceImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imageLoction = System.getProperty("user.dir") + screenShotPath;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actulImage = imageLoction + imageName + "_" + simpledateformat.format(calendar.getTime()) + ".png";
		File destinationImage = new File(actulImage);
		FileUtils.copyFile(sourceImage, destinationImage);
		return actulImage;
	}

	public static WebElement getLocator(String locator) throws Exception {
		String split[] = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			return driver.findElement(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			return driver.findElement(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			return driver.findElement(By.linkText(locatorValue));
		} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
			return driver.findElement(By.partialLinkText(locatorValue));
		} else if (locatorType.equalsIgnoreCase("cssSelector")) {
			return driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(locatorValue));
		} else
			throw new Exception("Unknown Locator Type :" + locatorType);

	}

	public List<WebElement> getLocators(String locator) throws Exception {
		String split[] = locator.split(":");
		String locatorType = split[0];
		String locatorVlue = split[1];

		if (locatorType.equalsIgnoreCase("id"))
			return driver.findElements(By.id(locatorVlue));
		else if (locatorType.equalsIgnoreCase("name"))
			return driver.findElements(By.name(locatorVlue));
		else if (locatorType.equalsIgnoreCase("className"))
			return driver.findElements(By.className(locatorVlue));
		else if (locatorType.equalsIgnoreCase("tagName"))
			return driver.findElements(By.tagName(locatorVlue));
		else if (locatorType.equalsIgnoreCase("linkText"))
			return driver.findElements(By.linkText(locatorVlue));
		else if (locatorType.equalsIgnoreCase("partialLinkText"))
			return driver.findElements(By.partialLinkText(locatorVlue));
		else if (locatorType.equalsIgnoreCase("cssSelector"))
			return driver.findElements(By.cssSelector(locatorVlue));
		else if (locatorType.equalsIgnoreCase("xpath"))
			return driver.findElements(By.xpath(locatorVlue));
		else
			throw new Exception("Unknown Locator Type :" + locatorType);

	}

	public static WebElement getWebElement(String object) throws Exception {
		logger.info("Object Fetched From Property File : " + object);
		return getLocator(BaseClass.properties.getProperty(object));
	}

	public List<WebElement> getWebElements(String object) throws Exception {
		logger.info("Object Fetched From Property File : " + object);
		return getLocators(BaseClass.properties.getProperty(object));

	}

	public String[][] getData(String excelName, String sheetName) {
		String excelLocation = System.getProperty("user.dir") + excelFilePath + excelName;
		excelreader = new ExcelReader();
		return excelreader.getExcelData(excelLocation, sheetName);

	}

	public static String getTestData(String property) {
		file = new File(System.getProperty("user.dir") + propertyFilePath);
		try {
			fileinputstream = new FileInputStream(file);

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}

		try {
			properties.load(fileinputstream);

		} catch (IOException exception) {
			exception.printStackTrace();
		}

		String PropData = properties.getProperty(property);
		logger.info("The Data from Property File is : " + PropData);

		try {

			String dataFromPropertyFile = PropData.trim();
			return dataFromPropertyFile;

		} catch (IllegalStateException exception) {
			exception.printStackTrace();
			return null;
		}

	}

}