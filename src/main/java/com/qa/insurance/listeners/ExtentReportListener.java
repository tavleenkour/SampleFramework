package com.qa.insurance.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.insurance.util.ScreenshotUtil;


// ********* Implement extent reports using ITestListner interface *********
public class ExtentReportListener implements ITestListener  
{
	
		private static final String OUTPUT_FOLDER = "./build/";
		private static final String FILE_NAME = "TestExecutionReport.html";
		private static ExtentReports extent = init();
		public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

		private static ExtentReports init() 
		{

			Path path = Paths.get(OUTPUT_FOLDER);
			if (!Files.exists(path)) 
			{
				try 
				{
					Files.createDirectories(path);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
			htmlReporter.config().setDocumentTitle("Automation Test Results");
			htmlReporter.config().setReportName("Automation Test Results");
			htmlReporter.config().setCSS(".r-img { width: 30%; }");
			htmlReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setReportUsesManualConfiguration(true);

			return extent;
		}

		public synchronized void onStart(ITestContext context) 
		{

		}

		public synchronized void onFinish(ITestContext context) 
		{
			extent.flush();
			test.remove();
		}

		public synchronized void onTestStart(ITestResult result) 
		{
			String qualifiedName = result.getMethod().getQualifiedName();
			int last = qualifiedName.lastIndexOf(".");
			int mid = qualifiedName.substring(0, last).lastIndexOf(".");
			String className = qualifiedName.substring(mid + 1, last);

			ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), 
					result.getMethod().getDescription());

			extentTest.assignCategory(result.getTestContext().getSuite().getName());
			extentTest.assignCategory(className);
			test.set(extentTest);
			test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		}

		public synchronized void onTestSuccess(ITestResult result) 
		{
			
			test.get().pass("Test passed");
			test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		}

		public synchronized void onTestFailure(ITestResult result) 
		{
			
			try {
				
				// ******** Capturing screenshots for failed tests. ********
				test.get().fail(result.getThrowable(),
						MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.takeScreenshotAtEndOfTest()).build()); 
			} catch (IOException e) {
				System.err.println("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
			}
			test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		}

		public synchronized void onTestSkipped(ITestResult result) 
		{
			
			try {
				
				// ******** Capturing screenshots for skipped tests. ********
				test.get().skip(result.getThrowable(),
						MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.takeScreenshotAtEndOfTest()).build());
			    
			} catch (IOException e) {
				System.err
						.println("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
			}
			test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		}

		public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) 
		{
			
		}
		
		
		// ******* Get the current time milliseconds ******
		private Date getTime(long millis) 
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			return calendar.getTime();
		}
	}