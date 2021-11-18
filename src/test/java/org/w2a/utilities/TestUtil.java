package org.w2a.utilities;

import java.io.File;
import java.io.IOException;
//import java.util.Date;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
}
