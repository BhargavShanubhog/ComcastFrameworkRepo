package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import POM.CreatingNewOrganizationPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

import java.io.FileReader;

public class CreateOrgWithIndustry {

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
		
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click(); */ 
		
		ExcelUtility elib=new ExcelUtility();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		String industry=elib.getDataFromExcel1("Sheet2", 1, 3);
		String type=elib.getDataFromExcel1("Sheet2", 1, 4);
		
		
		
		System.out.println(organization); 
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage l4=new CreatingNewOrganizationPage(driver);
		l4.createOrg(organization, industry);
		
		 
		
		/*driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(organization);*/
		//add  industry
		/*WebElement i = driver.findElement(By.name("industry"));
		Select sindustry=new Select(i);
		sindustry.selectByVisibleText(industry);*/
		
		
		//add type
		l4.Type(type);
		/*WebElement t = driver.findElement(By.name("accounttype"));
		Select stype=new Select(t);
		stype.selectByVisibleText(type);
		
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();*/
	    
	    String actualindustry = driver.findElement(By.id("dtlview_Industry")).getText();
	     String actualtype = driver.findElement(By.id("dtlview_Type")).getText();
	    
	    //verify organization header
	    if(actualindustry.equals(industry))
	    {
	    	System.out.println(actualindustry+ "is =" +industry);
	    }
	    
	    else
	    {
	    	System.out.println(actualindustry+ "=======verification not succesfull=========");
	    }
	    
	    //verify organization name
	    if(actualtype.equals(type))
	    {
	    	System.out.println(actualtype +"=" +type);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	   l2.SignOut();

	}

}
