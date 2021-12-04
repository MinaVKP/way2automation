package org.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.w2a.bases.TestBaseClass;
import org.w2a.utilities.TestUtil;

public class OpenAccountTest extends TestBaseClass {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
//	public void openAccountTest(String customer, String currency) throws InterruptedException
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException
	{
		click("openAccountbtn_CSS");
		type("customer_CSS",data.get("customer"));
		type("currency_CSS",data.get("currency"));
		click("processbtn_CSS");
		
		Thread.sleep(2000);
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	
}
