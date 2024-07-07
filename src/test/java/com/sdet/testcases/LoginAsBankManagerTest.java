package com.sdet.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sdet.base.TestBase;

public class LoginAsBankManagerTest extends TestBase {
	
	@Test
	public static void LoginAsBankManager()
	{
		driver.findElement(By.cssSelector(OR.getProperty("bmlogin_btn"))).click();
		log.debug("clicked on login as bank manager");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("bmAddCustomer_btn"))));
		log.debug("Add customer is visible");
		System.out.println("success");
		
	}

}
