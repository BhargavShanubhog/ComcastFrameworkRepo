package ListnerUtility;

import java.io.File;



import java.io.IOException;
import java.util.Date;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Baseclass.BaseClass;
import generic.UtilityClassObjects;

public class ListImpClass implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public static ExtentTest test;
	String time=null;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		 time=new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./Advancereport/"+suite.getName()+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+"==START==");
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObjects.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"==STARTED==");
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+"===END===");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==COMPLETED==");
	}

	

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		TakesScreenshot edriver=(TakesScreenshot)UtilityClassObjects.getDriver();
		
		String srcfile=edriver.getScreenshotAs(OutputType.BASE64);
		 time=new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(srcfile, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==FAILED==");
		
	
	
	}

	


	
	
	

}
