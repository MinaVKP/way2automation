package org.w2a.rough;

import java.util.Date;

import org.w2a.utilities.TestUtil;

public class timestampscreenshot extends TestUtil{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
//			Date d = new Date();
//			String screenshotName = d.toString().replace(":", "_").replace(" ","_");
//			System.out.println(screenshotName+".jpg");
//			System.out.println(d);
//			
//			System.out.println(System.getProperty("user.dir"));
		
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshots\\"+TestUtil.screenshotName);
	}

}
