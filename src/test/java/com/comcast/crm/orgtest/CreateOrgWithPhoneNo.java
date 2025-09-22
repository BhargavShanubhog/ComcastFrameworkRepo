package com.comcast.crm.orgtest;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import POM.CreatingNewOrganizationPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationInfoPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;

public class CreateOrgWithPhoneNo {

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
		String phno =elib.getDataFromExcel1("Sheet2", 1, 5)+jv.getRandomNumber();
		
		
		System.out.println(organization);  
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
		
		/*CreatingNewOrganizationPage l4=new CreatingNewOrganizationPage(driver);
		l4.createOrg(organization);*/
	
		 
		
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(organization); 
		
		//add phone number
		driver.findElement(By.name("phone")).sendKeys(phno);
	    driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	    
	    OrganizationInfoPage l=new OrganizationInfoPage(driver);
	    String actualtext=l.getHeaderMsg().getText();
	    
	   // String actualtext = driver.findElement(By.className("dvHeaderText")).getText();
	     String orgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
	     String actualph = driver.findElement(By.id("dtlview_Phone")).getText();
	    
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
	    	System.out.println("==orgname not matching==");
	    }
	    //verify phonenumber
	    if(phno.equals(actualph))
	    {
	    	System.out.println(phno +"=" +actualph);
	    }
	    else
	    {
	    	System.out.println("==phno not matching==");
	    }
	    l2.SignOut();

	}

}
