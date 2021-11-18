package org.w2a.bases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.w2a.utilities.ExcelReader;
import org.w2a.utilities.ExtentManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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