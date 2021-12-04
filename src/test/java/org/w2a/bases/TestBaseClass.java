package org.w2a.bases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.w2a.utilities.ExcelReader;
import org.w2a.utilities.ExtentManager;
import org.w2a.utilities.TestUtil;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBaseClass {

		/*Initialize
		 * 
		 * WebDriver
		 * Properties
		 * log
		 * ExtentReports
		 * DB
		 * Excel
		 * Mail
		 * ReportNG, Extent Reports
		 * Jenkins
		 * 
		 */

public static WebDriver driver;
public static Properties config= new Properties();
public static Properties objrepo = new Properties();
public static FileInputStream fis;
public static Logger log = Logger.getLogger("devpinoyLogger");
public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\w2a.testdata.xlsx");
public static WebDriverWait wait;
public ExtentReports report = ExtentManager.getInstance();
public static ExtentTest test;


@BeforeSuite
public void setUp()
{
//	System.out.println(System.getProperty("user.dir"));
	if (driver == null)
	{
							
		try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			}
				
		try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		
		try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\ObjRepo.properties");
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			}
				
		try {
				objrepo.load(fis);
				log.debug("ObjRepo file loaded");
				} catch (IOException e) {
					e.printStackTrace();
			}
				
				
//			Open a Browser
			if((config.getProperty("browser")).equalsIgnoreCase("Googlechrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//src//test//resources//executables//chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Browser Launched");
			}
			else if((config.getProperty("browser")).equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"//src//test//resources//executables//geckodriver.exe");	
				driver=new FirefoxDriver();
				log.debug("Firefox Browser Launched");
			}
			else if(config.getProperty("browser").equals("ie"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"//src//test//resources//executables//IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				log.debug("Internet Explorer Launched");
			}
							
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Website opened");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait((Integer.parseInt(config.getProperty("implicit_wait"))),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
	}
}

public void click(String locator)
{
	if(locator.endsWith("_CSS"))
	{
		driver.findElement(By.cssSelector(objrepo.getProperty(locator))).click();
	}
	else if(locator.endsWith("_XPATH"))
	{
		driver.findElement(By.xpath(objrepo.getProperty(locator))).click();
	}
	else if(locator.endsWith("_ID"))
	{
		driver.findElement(By.id(objrepo.getProperty(locator))).click();
	}
	test.log(LogStatus.INFO,"Clicking on " + locator);
}

public void type(String locator,String value)
{
	if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(objrepo.getProperty(locator))).sendKeys(value);
	}
	else if(locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(objrepo.getProperty(locator))).sendKeys(value);
	}
	else if(locator.endsWith("_ID")) {
		driver.findElement(By.id(objrepo.getProperty(locator))).sendKeys(value);
	}

	test.log(LogStatus.INFO,"Typing in  " + locator + " and entered the value "+ value);
}

public boolean isElementPresent(By by)
{
	try
	{
		driver.findElement(by);
		return true;
		
	}catch(NoSuchElementException e) {
		return false;
	}
	
}

public static void verifyEquals(String actual, String expected) throws Exception
{
	try
	{
		Assert.assertEquals(actual, expected);
	}catch (Throwable t) {
		 
		TestUtil.captureScreenshot();
		//Reportng report
		Reporter.log("<br>" + "Verification failure :"+t.getMessage()+"<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src= "+TestUtil.screenshotName+" height = 200 width=200></img></a>");
		Reporter.log("<br>");
		
		//Extent Report
		test.log(LogStatus.FAIL,"Verification Failed with Exception"+t.getMessage());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		
	}
	
}
			
@AfterSuite
public void teardown()
{
				 
	if(driver!=null)
	{
		driver.quit();
					 
	}
	log.debug("Test Execution Completed");
}

}