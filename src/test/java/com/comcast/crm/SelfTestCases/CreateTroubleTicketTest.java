package com.comcast.crm.SelfTestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import POM.CreatingTroubleTicketPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.JavaUtility;
import generic.JsonUtility;
import generic.WebDriverUtility;

public class CreateTroubleTicketTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver;
		JsonUtility j=new JsonUtility();
		String URL=j.getDataFromJsonFile("url");
		String BROWSER=j.getDataFromJsonFile("browser");
		String UN=j.getDataFromJsonFile("username");
		String PWD=j.getDataFromJsonFile("password");
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
			
		}else {
			driver=new ChromeDriver();
		}
		
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
		driver.get(URL);
		driver.manage().window().maximize();
		WebDriverUtility w=new WebDriverUtility();
		w.waitforPageLoad(driver);
		
		ExcelUtility e=new ExcelUtility();
		String title=e.getDataFromExcel1("Sheet4", 1, 0);
		
		LoginPage l=new LoginPage(driver);
		l.loginToApp(UN, PWD);
		
		CreatingTroubleTicketPage ct=new CreatingTroubleTicketPage(driver);
		ct.getTt().click();
		
		
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		ct.getTitle().sendKeys(title+h1);
		System.out.println(title+h1);
		
		ct.getJj().click();
		
		ct.getTt().click();
		
		w.select(ct.getSf(), "Title");
		
		ct.getTx().sendKeys(title+h1);
		
		ct.getButton().click();
		
		driver.findElement(By.xpath("//a[text()='"+title+h1+"']/../../td[8]/a[text()='del']")).click();
		Alert d=driver.switchTo().alert();
		d.accept();
		
		Homepage h=new Homepage(driver);
		h.SignOut();
		driver.quit();
		
		

	}

}
