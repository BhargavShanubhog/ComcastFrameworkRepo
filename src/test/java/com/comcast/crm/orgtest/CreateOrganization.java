package com.comcast.crm.orgtest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import POM.CreatingNewOrganizationPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

import java.io.FileReader;

public class CreateOrganization {

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
		
		driver.manage().window().maximize();
		wb.waitforPageLoad(driver);
		
		LoginPage ll=new LoginPage(driver);
		ll.loginToApp(USERNAME, PASSWORD);
		
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		/*driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();*/
		
		ExcelUtility elib=new ExcelUtility();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		
		System.out.println(organization);  
		
		//driver.findElement(By.linkText("Organizations")).click();
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		CreatingNewOrganizationPage l4=new CreatingNewOrganizationPage(driver);
		l4.createOrg(organization);
		//driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organization);
		//driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		String actualtext = driver.findElement(By.className("dvHeaderText")).getText();
	     String orgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
	    
	    //verify organization header
	    if(actualtext.contains(organization))
	    {
	    	System.out.println(organization+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(organization+ "=======verification not succesfull=========");
	    }
	    
	    //verify organization name
	    if(orgname.equals(organization))
	    {
	    	System.out.println(organization +"=" +orgname);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
		
	    l2.SignOut();

	}

}
