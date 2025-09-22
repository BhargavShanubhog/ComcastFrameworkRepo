package com.comcast.crm.orgtest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import POM.CreatingNewOrganizationPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

public class DeleteOrgTest {

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
		String organization  =elib.getDataFromExcel1("Sheet2", 2, 0)+jv.getRandomNumber();
		
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
	    
	    //go back to Organization page
	    
		l2.getOrgLink().click();
		
		//search for Organization
		l3.getSearch().sendKeys(organization);
		wb.select(l3.getSearch_f(), elib.getDataFromExcel1("Sheet2", 1, 6));
		l3.getR().click();
		
		driver.findElement(By.xpath("//a[text()='"+organization+"']/../../td[8]/a[text()='del']")).click();
		Alert d=driver.switchTo().alert();
		d.accept();
		
		
	    
	    
		
	    
	    l2.SignOut();
	    driver.quit();


	}

}
