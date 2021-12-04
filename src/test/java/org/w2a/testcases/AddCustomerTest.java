package org.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w2a.bases.TestBaseClass;
import org.w2a.utilities.TestUtil;


public class AddCustomerTest extends TestBaseClass {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
//	public void addCustomerTest(String firstName, String lastName, String postcode,String alertText,String runmode) throws Exception
	public void addCustomerTest(Hashtable<String,String> data) throws Exception
	{
		
			
		if(!data.get("RunMode").equals("Y"))
		{
			throw new SkipException("Skipping data as the runmode for data set is set to NO");
		}
		
		click("addCustbtn_CSS");
		
		type("firstname_CSS",data.get("FirstName"));	
		type("lastname_XPATH",data.get("LastName"));
		type("postcode_CSS",data.get("Postal Code"));
		Thread.sleep(3000);
		click("addbtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertText")));
		alert.accept();
		//Assert.fail("Customer not added");
			
	}

	

	
}
