package org.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
//import java.util.Date;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.w2a.bases.TestBaseClass;

public class TestUtil extends TestBaseClass{
			
	public static String screenshotName;	
	public static String screenshotpath;
	public static void captureScreenshot() throws IOException
	{
		File scrshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ","_")+".jpg";
	    	
		FileUtils.copyFile(scrshot, new File(System.getProperty("user.dir")+"//target//surefire-reports//html//" + screenshotName));
		
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	
	{
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
			
		Object[][] data= new Object[rows-1][1];
		
		Hashtable<String,String> table = null;
		
		for(int rowcount = 2; rowcount<=rows; rowcount++)
		{
			//creating a new hashtable for each row
			table = new Hashtable<String, String>();
			
			for (int colcount = 0; colcount<cols; colcount++)
			{
				table.put(excel.getCellData(sheetName,colcount,1), excel.getCellData(sheetName,colcount,rowcount));
//				data[rowcount-2][colcount] = table;
				data[rowcount-2][0] = table;
				
			}
		}
		
		return data;
	}
	
	public static boolean isTestRunnable(String testname, ExcelReader excel)
	{
		String sheetName = "TestSuite"; 
		int rowcount = excel.getRowCount(sheetName); 
		
		for(int rNum = 2; rNum <= rowcount; rNum++)
		{
			String testcase = excel.getCellData(sheetName, "TestCaseID", rNum);
			if(testcase.equalsIgnoreCase(testname))
			{
				String runmode = excel.getCellData(sheetName, "RunMode", rNum);
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
				
		return false;
	}
}
