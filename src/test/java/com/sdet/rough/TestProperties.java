package com.sdet.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
//		The user.dir system property holds the absolute path of the directory 
//		from which the Java virtual machine was invoked.
//		System.out.println(System.getProperty("user.dir"));
		
		Properties Config = new Properties();
		Properties OR = new Properties();
		
//		to read the config.properties file.
		// Load the properties file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		Config.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		
	
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
//		System.out.println(Config.getProperty("browser"));
//		System.out.println(OR.getProperty("bmlogin_btn"));
	}

}
