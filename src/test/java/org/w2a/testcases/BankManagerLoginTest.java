package org.w2a.testcases;



//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w2a.bases.TestBaseClass;

public class BankManagerLoginTest extends TestBaseClass{
		
	@Test
	public void bankManagerLoginTest() throws Exception {
		Thread.sleep(3000);
		verifyEquals("abc","xyz");
		
		log.debug("Inside LoginTest");
		click("bnkmgrbtn_CSS");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(objrepo.getProperty("addCustbtn_CSS"))));
		
		log.debug("LoginTest Successful");
		//Assert.fail("login failed");
		
	}   
	

}

