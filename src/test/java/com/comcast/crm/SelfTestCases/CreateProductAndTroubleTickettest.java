package com.comcast.crm.SelfTestCases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import POM.CreatingProductAndTicket;
import POM.CreatingProductTest;
import POM.CreatingTroubleTicketPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.JavaUtility;
import generic.JsonUtility;
import generic.UtilityClassObjects;
import generic.WebDriverUtility;

public class CreateProductAndTroubleTickettest {

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
		String title=e.getDataFromExcel1("Sheet4", 1, 0);
		
		LoginPage l=new LoginPage(driver);
		l.loginToApp(UN, PWD);
		
		Homepage h=new Homepage(driver);
		h.getPro().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingProductTest cp=new CreatingProductTest(driver);
		cp.getY().sendKeys(product+h1);
		System.out.println(product+h1);
		String product1=product+h1;
		
		cp.getJj().click();
		
		h.getPro().click();
		
		CreatingTroubleTicketPage ct=new CreatingTroubleTicketPage(driver);
		ct.getTt().click();
		
		op.getCreateNewOrgBtn().click();
		ct.getTitle().sendKeys(title+h1);
		ct.getProduct().click();
		
		
		ct.Switch();
		ct.getProduct1().sendKeys(product+h1);
		WebDriverUtility wd=new WebDriverUtility();
		wd.select(ct.getDd(), "Product Name");
		
		ct.getSearch().click();
		
		driver.findElement(By.linkText(product1)).click();
		
		ct.Switch();
		CreatingProductAndTicket gg=new CreatingProductAndTicket(driver);
		gg.getJj().click();
		h.SignOut();
		driver.quit();
		
		

	}

}
