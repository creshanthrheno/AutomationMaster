package com.ManageUtils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {

	static WebDriver driver;
	
	public static WebDriver launchBrowser()
	{
		try
		{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\Data.properties");
			prop.load(fis);
			String browserName = prop.getProperty("Browser");
			
			switch (browserName) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
	        
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			case "IE":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			
			}
			driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
		}
		catch (Exception e) {
		   System.out.println(e.getMessage());
		}
		return driver;
	}
}
