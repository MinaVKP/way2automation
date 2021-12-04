package org.w2a.listeners;


import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.w2a.bases.TestBaseClass;
import org.w2a.utilities.TestUtil;

import com.relevantcodes.extentreports.LogStatus;



public class CustomListeners extends TestBaseClass implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		 test = report.startTest(result.getName().toUpperCase());
		 
		 //run mode 
		 if(!TestUtil.isTestRunnable(result.getName(), excel))
		 {
			 throw new SkipException("Skipping Exception " + result.getName().toUpperCase() + "as the runmode is NO");
		 }
		 
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase(), "Passed");
		report.endTest(test);
		report.flush();
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		Reporter.log("Click to view Screenshot");
		
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase(), "Failed with Exception"+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src= "+TestUtil.screenshotName+" height = 200 width=200></img></a>");
		
		report.endTest(test);
		report.flush();
		
						
//		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\priya\\eclipse-workspace\\Selenium Projects\\org.w2a.DataDrivenFramework\\src\\test\\resources\\Screenshots\\XYZBank.bmp\">Screenshot</a>");
//		Reporter.log("<br>");
//		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\priya\\eclipse-workspace\\Selenium Projects\\org.w2a.DataDrivenFramework\\src\\test\\resources\\Screenshots\\XYZBank.bmp\"><img src=\"C:\\Users\\priya\\eclipse-workspace\\Selenium Projects\\org.w2a.DataDrivenFramework\\src\\test\\resources\\Screenshots\\XYZBank.bmp\" height = 200 width=200></img></a>");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase(), "Skipped with Exception as runmode is NO");
		report.endTest(test);
		report.flush();
		
	}

	@Override	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	
	}
}
