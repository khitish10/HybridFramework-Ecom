package com.hybridecom.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.hybridecom.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();//Create object of ReadConfig class to access its method
	
	public String baseURL=readConfig.getApplicationURL();
	public String username=readConfig.getuserEmail();
	public String password=readConfig.getpassword();
	public  WebDriver driver;
	
	public static Logger logger;//object to create log
	
	@Parameters("browser")
	@BeforeClass
	public  void setup(String browserName) {
		
		logger=Logger.getLogger("HybridECom");//instantiate with project name
		PropertyConfigurator.configure("log4j.properties");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",readConfig.getchromepath());
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readConfig.getfirefoxpath());
			driver = new FirefoxDriver();
		}
		
		
		
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
