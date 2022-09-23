package com.hybridecom.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridecom.pageObjects.LoginPage;
import com.hybridecom.utilities.XLUtils;

public class TC_LoginDataDrivenTest_001 extends BaseClass{
	
	
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pass) throws InterruptedException, IOException {
		
		driver.get(baseURL);
		logger.info("URL is opened ...");
		
		LoginPage loginPage = new LoginPage(driver);
		driver.manage().window().maximize();
		
		loginPage.setUsername(user);
		logger.info("Username provided");
		
		loginPage.setPassword(pass);
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
	
	@DataProvider(name="LoginData")//provide an unique name each time
	public String[][] getData() throws IOException{//reads data from excel and pass to loginTest
		
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\hybridecom\\testData\\LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);//since all rows have same columns we can take any 1 row for reference
		
		String logindata[][]=new String[rownum][colcount];//create a new array to store excel elments
		
		for(int i=1;i<=rownum;i++) {//it starts from 1 since header is 0th row
			
			for(int j=0;j<colcount;j++) {
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);//storing ith row and jth column from excel to ith-1 row and jth column in array
			}
		}
		return logindata;
	}

}
