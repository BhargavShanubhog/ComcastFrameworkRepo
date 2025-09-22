package com.comcast.crm.contactTest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

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

public class CreateContactWithOrgTest {

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
		
		//login
		LoginPage ll=new LoginPage(driver);
		ll.loginToApp(USERNAME, PASSWORD);
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();*/       
		
		
		//fetch org from excel
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		
		Homepage h=new Homepage(driver);
		
		 
		//navigate to organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		
		//enter the org name
		driver.findElement(By.name("accountname")).sendKeys(organization);
	    driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	    Thread.sleep(2000);
	    
	    //navigate to contacts
         driver.findElement(By.xpath("//a[@href=\"index.php?module=Contacts&action=index\"]")).click();
		
         //enter all the details
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
		wb.switchToTabonURL(driver, "module=Accounts");
		/*Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String acturl = driver.getCurrentUrl();
			
			if(acturl.contains("module=Accounts"))
			{
				break;
			}
		}*/
		
		
	Thread.sleep(4000);	
	driver.findElement(By.name("search_text")).sendKeys(organization);
	Thread.sleep(4000);	
	driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
	
	//driver.findElement(By.xpath("//a[@text()='"+organization+"']")).click();
	Thread.sleep(4000);	
	driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
	
	//switch to parent window
	wb.switchToTabonURL(driver, "Contacts&action");
	/*Set<String> set1 = driver.getWindowHandles();
	Iterator<String> it1 = set1.iterator();
	while(it1.hasNext())
	{
		String windowID1 = it1.next();
		driver.switchTo().window(windowID1);
		
		String acturl = driver.getCurrentUrl();
		
		if(acturl.contains("Contacts&action"))
		{
			break;
		}
	}*/
	
	    driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	    
	   
	     String orgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	    
	    //verify organization header
	    if(orgname.contains(organization))
	    {
	    	System.out.println(organization+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(organization+ "=======verification not succesfull=========");
	    }
	    
	    h.SignOut();
	 
	   
	    
	    driver.quit();
	}

	}


