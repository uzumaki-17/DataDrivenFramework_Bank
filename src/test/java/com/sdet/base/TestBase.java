package com.sdet.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	/*
	 * Webdriver -- done
	 * logs  log4j jar, .log files, log4j.properties
	 * mail
	 * Properties -- done
	 * ExtentReports
	 * DB
	 * Excel
	 * */
	
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static Logger log = LoggerFactory.getLogger(TestBase.class);
//	public static WebDriverWait wait = new WebDriverWait(driver, null);
//	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	
	@BeforeSuite
	public void setUp()
	{
//		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");
		if(driver==null)
		{
//			FileInputStream fis;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("Config Loaded");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("ChromeDriver  Loaded");
				
			}
			else if(Config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				
				FirefoxOptions options = new FirefoxOptions();
	                // Set the path to the Firefox binary, if it's not in the default location
	            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // Change this to the actual path of your Firefox installation
				driver = new FirefoxDriver(options);
				log.debug("FireFox Loaded");
			}
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("testsiteURL Loaded");
			driver.manage().window().maximize();
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		}
		
	}
	
	public static boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e)
		{
			return false;
		}
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
			log.debug("driver quit");
		}
	}

}
