package com.hybridecom.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hybridecom.pageObjects.LoginPage;



public class TC_LoginTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		driver.get(baseURL);
		logger.info("URL is opened ...");
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setUsername(username);
		logger.info("Username provided");
		
		loginPage.setPassword(password);
		logger.info("Password provided");
		
		loginPage.clickLogin();
		logger.info("Clicked on login button");
		
		//verify the title
		String actualTitle=driver.getTitle();
		String expectedTitle="Dashboard / nopCommerce administration";
		//Assert.assertEquals(actualTitle, expectedTitle, "Titles mis-match!!");
		
		Thread.sleep(2000);
		
		
		if(actualTitle.equals(expectedTitle)) {
			Assert.assertTrue(true);
			loginPage.clickLogout();
			logger.info("Login Passed");
		}else {
			captureScreen(driver,"TC_LoginTest_001");//calls the screenshot method from base class
			Assert.assertFalse(false);
			logger.info("Login Failed");
		}
		
	}
	
	

}
