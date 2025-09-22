package ExtentReport;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import POM.CreatingProductAndTicket;
import POM.CreatingProductTest;
import POM.CreatingTroubleTicketPage;
import POM.Homepage;
import POM.LoginPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;
import generic.JavaUtility;
import generic.WebDriverUtility;

public class CreateProductAndTroubleTicket extends BaseClass{
	
	@Test
	public void CreateProductAndTroubleTickettest() throws Throwable {
		ExcelUtility e=new ExcelUtility();
		String product=e.getDataFromExcel1("sheet2", 1, 7);
		String title=e.getDataFromExcel1("Sheet4", 1, 0);
		
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
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
	}
	
	@Test
	public void CreateProductSalesAndSupport() throws Throwable {
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
		ExcelUtility e=new ExcelUtility();
		String product=e.getDataFromExcel1("sheet2", 1, 7);
		
		Homepage h=new Homepage(driver);
		h.getPro().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingProductTest cp=new CreatingProductTest(driver);
		cp.getY().sendKeys(product+h1);
		System.out.println(product+h1);
		
		cp.getSales_start().sendKeys(jv.getSystemdateYYYYDDMM());
		cp.getSales_end().sendKeys(jv.getSystemdateYYYYDDMM(30));
		
		cp.getSupport_start().sendKeys(jv.getSystemdateYYYYDDMM());
		cp.getSupport_end().sendKeys(jv.getSystemdateYYYYDDMM(30));
		
		System.out.println(jv.getSystemdateYYYYDDMM());
		System.out.println(jv.getSystemdateYYYYDDMM(30));
		
		cp.getJj().click();
		
	}
	
	@Test
	public void CreateProductSalesDate() throws Throwable {
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
		ExcelUtility e=new ExcelUtility();
		String product=e.getDataFromExcel1("sheet2", 1, 7);
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
		
		
	}
	
	@Test
	public void CreateProductTest() throws Throwable {
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
		ExcelUtility e=new ExcelUtility();
		String product=e.getDataFromExcel1("sheet2", 1, 7);
		
		Homepage h=new Homepage(driver);
		h.getPro().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingProductTest cp=new CreatingProductTest(driver);
		cp.getY().sendKeys(product+h1);
		System.out.println(product+h1);
		
		cp.getJj().click();
		
	}
	
	@Test
	public void CreateTroubleTicketTest() throws Throwable {
		JavaUtility jv=new JavaUtility();
		int h1=jv.getRandomNumber();
		
		ExcelUtility e=new ExcelUtility();
		String title=e.getDataFromExcel1("Sheet4", 1, 0);
		
		CreatingTroubleTicketPage ct=new CreatingTroubleTicketPage(driver);
		ct.getTt().click();
		
		
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		ct.getTitle().sendKeys(title+h1);
		System.out.println(title+h1);
		
		ct.getJj().click();
		
		ct.getTt().click();
		WebDriverUtility w=new WebDriverUtility();
		w.waitforPageLoad(driver);
		
		w.select(ct.getSf(), "Title");
		
		ct.getTx().sendKeys(title+h1);
		
		ct.getButton().click();
		
		driver.findElement(By.xpath("//a[text()='"+title+h1+"']/../../td[8]/a[text()='del']")).click();
		Alert d=driver.switchTo().alert();
		d.accept();
		
	}

}
