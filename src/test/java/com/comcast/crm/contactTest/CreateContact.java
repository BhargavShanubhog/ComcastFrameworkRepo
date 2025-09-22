package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;

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
import org.openqa.selenium.interactions.Actions;

import POM.Homepage;
import POM.LoginPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

public class CreateContact{

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
		
		
		LoginPage ll=new LoginPage(driver);
		ll.loginToApp(USERNAME, PASSWORD);
		/*driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();*/
		
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		
		Homepage h=new Homepage(driver);
		
		 
		
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		String ln = driver.findElement(By.id("dtlview_Last Name")).getText();
	     String header = driver.findElement(By.className("dvHeaderText")).getText();
	    
	    //verify contact header
	    if(header.contains(lastname))
	    {
	    	System.out.println(lastname+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(lastname+ "=======verification not succesfull=========");
	    }
	    
	    //verify organization name
	    if(ln.equals(lastname))
	    {
	    	System.out.println(lastname +"=" +ln);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	    h.SignOut();
		
        driver.quit();
	}

}
