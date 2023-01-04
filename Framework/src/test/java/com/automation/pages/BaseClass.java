package com.automation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.automation.utility.Browser;
import com.automation.utility.ConfigDataProvider;
import com.automation.utility.ExcelDataProvider;
import com.automation.utility.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	
	@BeforeSuite
	public void setUpSuite()
	{
		
		Reporter.log("Setting up reports and Test is getting ready", true);
		excel= new ExcelDataProvider();
		config = new ConfigDataProvider(); 
		
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Karthick"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Settings Done - Test can be started", true);
		
	}
	// test commit
	  
	
	@BeforeClass
	public void setUp()
	{
		
		Reporter.log("Start Browser", true);
		driver = Browser.startApplication(driver, config.getBrowser(), config.getStagingURL());
		
		Reporter.log("Browser ready", true);
	}
	
	@AfterClass
	public void tearDown()
	{
		Browser.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		
		Reporter.log("Test will end now", true);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
		
		Reporter.log("Test Completed. Reports Generated", true);
	

	}

}
