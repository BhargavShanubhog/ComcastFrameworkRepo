package ConfigAnnotation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import POM.CreatingNewOrganizationPage;
import POM.Homepage;
import POM.OrganizationInfoPage;
import POM.OrganizationsPage;
import generic.ExcelUtility;

public class CreateOrgTest extends BaseClass {
	
	@Test
	public void createOrganization() throws Throwable {
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		ExcelUtility elib=new ExcelUtility();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		
		System.out.println(organization);  
		
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage l4=new CreatingNewOrganizationPage(driver);
		l4.createOrg(organization);
		
		String actualtext = driver.findElement(By.className("dvHeaderText")).getText();
	    String orgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
	    
	    if(actualtext.contains(organization))
	    {
	    	System.out.println(organization+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(organization+ "=======verification not succesfull=========");
	    }
	    
	    if(orgname.equals(organization))
	    {
	    	System.out.println(organization +"=" +orgname);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	}
	
	@Test
	public void CreateOrgWithIndustry() throws Throwable {
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
		
		 
		
		
		
		
		//add type
		l4.Type(type);
		
	    
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
	}
	
	@Test
	public void OrgWithPhoneNo() throws Exception {
		ExcelUtility elib=new ExcelUtility();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		String phno =elib.getDataFromExcel1("Sheet2", 1, 5)+jv.getRandomNumber();
		
		
		System.out.println(organization);  
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
	
		 
		
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
	}
	
	@Test
	public void DeleteOrgTest() throws Exception {
		Homepage l2=new Homepage(driver);
		l2.getOrgLink().click();
		
		
		ExcelUtility elib=new ExcelUtility();
		String organization  =elib.getDataFromExcel1("Sheet2", 2, 0)+jv.getRandomNumber();
		
		System.out.println(organization);  
		
		//driver.findElement(By.linkText("Organizations")).click();
		OrganizationsPage l3=new OrganizationsPage(driver);
		l3.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage l4=new CreatingNewOrganizationPage(driver);
		l4.createOrg(organization);
		
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
		
	}

}
