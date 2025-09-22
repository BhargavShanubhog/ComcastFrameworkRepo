package com.comcast.crm.SelfTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import POM.CreatingProductTest;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.JavaUtility;
import generic.JsonUtility;
import generic.UtilityClassObjects;
import generic.WebDriverUtility;

public class CreateProductSalesDate {

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
		String product=e.getDataFromExcel1("sheet2", 1, 7);
		
		LoginPage l=new LoginPage(driver);
		l.loginToApp(UN, PWD);
		
		Homepage h=new Homepage(driver);
		h.getPro().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingProductTest cp=new CreatingProductTest(driver);
		cp.getY().sendKeys(product+h1);
		System.out.println(product+h1);
		
		cp.getSales_start().sendKeys(jv.getSystemdateYYYYDDMM());
		cp.getSales_end().sendKeys(jv.getSystemdateYYYYDDMM(30));
		
		System.out.println(jv.getSystemdateYYYYDDMM());
		System.out.println(jv.getSystemdateYYYYDDMM(30));
		
		cp.getJj().click();
		
		h.SignOut();
		
		driver.quit();
		
		

	}

}





























































