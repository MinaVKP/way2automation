package org.w2a.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		
		Properties or = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\ObjRepo.properties");
		or.load(fis);
		
		System.out.println(config.getProperty("browser"));
		System.out.println(or.getProperty("bnkmgrbtn"));
		
	}

}
