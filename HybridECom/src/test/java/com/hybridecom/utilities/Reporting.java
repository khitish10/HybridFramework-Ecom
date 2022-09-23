package com.hybridecom.utilities;

//package com.nopcommerce.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	
	public ExtentHtmlReporter htmlReporter;//represents the template
	public ExtentReports extent;//sending the user information like local host, QA env, user
	public ExtentTest logger;//sends the status of the test cases like pass/fail
	
		
	public void onStart(ITestContext testContext)//this method automatically invoked when our test case starts
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";//name of th report
		
		//name is provided dynamically with timestamp
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");//load the extent-config.XML file
		
		extent=new ExtentReports();
		
		//Configure below according to user requirement
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("User","Khitish");
		extent.setSystemInfo("OS", "WINDOWS 10");		
		
		
		htmlReporter.config().setDocumentTitle("HybridECom Test Project"); // Title of report
		htmlReporter.config().setReportName("Functional Test Report"); // name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)//Executed when test method is passed
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		
		//Imeplemented screenshot on failure
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";//tr.getName() gives us the testcase name that allows to pick up right screenshot from screenshot path
		
		File f = new File(screenshotPath); 
		
		//Verify if screen shot exist or not
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));//attach the screenshot to our report
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}

