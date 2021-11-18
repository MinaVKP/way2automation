package org.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w2a.bases.TestBaseClass;


public class AddCustomerTest extends TestBaseClass {
	
	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postcode,String alertText) throws Exception
	{
		driver.findElement(By.cssSelector(objrepo.getProperty("addCustbtn"))).click();
		
		driver.findElement(By.cssSelector(objrepo.getProperty("firstname"))).sendKeys(firstName);	
		driver.findElement(By.cssSelector(objrepo.getProperty("lastname"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(objrepo.getProperty("postcode"))).sendKeys(postcode);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(objrepo.getProperty("addbtn"))).click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		Assert.fail("Customer not added");
			
	}

	
	@DataProvider
	public Object[][] getData()
	
	{
		String sheetName="AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
			
		Object[][] data= new Object[rows-1][cols];
		
		for(int rowcount = 2; rowcount<=rows;rowcount++)
		{
			for (int colcount =0; colcount<cols; colcount++)
			{
				data[rowcount-2][colcount] = excel.getCellData(sheetName,colcount,rowcount);
				
			}
		}
		
		return data;
		
		
	}
	
}
