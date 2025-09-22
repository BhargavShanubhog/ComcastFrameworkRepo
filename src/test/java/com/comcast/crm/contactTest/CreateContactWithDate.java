package com.comcast.crm.contactTest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import POM.Homepage;
import POM.LoginPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

import java.io.FileReader;

public class CreateContactWithDate {

	public static void main(String[] args) throws Throwable {
        FileUtility flib=new FileUtility();
        JavaUtility jv=new JavaUtility();
        WebDriverUtility wb=new WebDriverUtility();
		
		
		String BROWSER = flib.getDataFromProperties("browser");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
		String PASSWORD = flib.getDataFromProperties("password");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver=null; 
		
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(URL);
		wb.waitforPageLoad(driver);
		
		
		//login to module
		LoginPage ll=new LoginPage(driver);
		ll.loginToApp(USERNAME, PASSWORD);
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();*/
		
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		Homepage h=new Homepage(driver);
		
	
		 
		
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		
		
		//enter all the details
		
		String startdate=jv.getSystemdateYYYYDDMM();
		System.out.println(startdate);
		
		
		String enddate = jv.getSystemdateYYYYDDMM(30);
		System.out.println(enddate);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
	
		
		//startdate
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		
		//enddate
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
		//save button
	    driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	    
	    String ln = driver.findElement(By.id("dtlview_Last Name")).getText();
	     String header = driver.findElement(By.className("dvHeaderText")).getText();
	     String actualsd = driver.findElement(By.id("dtlview_Support Start Date")).getText();
	     String actualed = driver.findElement(By.id("dtlview_Support End Date")).getText();
	    
	    //verify contact header
	    if(header.contains(lastname))
	    {
	    	System.out.println(lastname+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(lastname+ "=======verification not succesfull=========");
	    }
	    
	    //verify lastname
	    if(ln.equals(lastname))
	    {
	    	System.out.println(lastname +"=" +ln);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	    //verify startdate
	    if(actualsd.equals(startdate))
	    {
	    	System.out.println(actualsd +"=" +startdate);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	    //verify endate
	    if(actualed.equals(enddate))
	    {
	    	System.out.println(actualed +"=" +enddate);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    h.SignOut();
	    
	    driver.quit();

	}

}
