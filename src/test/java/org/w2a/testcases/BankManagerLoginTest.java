package org.w2a.testcases;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w2a.bases.TestBaseClass;

public class BankManagerLoginTest extends TestBaseClass{
		
	@Test
	public void bankManagerLogin() throws InterruptedException {

		
		log.debug("Inside LoginTest");
		driver.findElement(By.cssSelector(objrepo.getProperty("bnkmgrbtn"))).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(objrepo.getProperty("addCustbtn"))));
		
		log.debug("LoginTest Successful");
		Assert.fail("login failed");
		
	}

}

