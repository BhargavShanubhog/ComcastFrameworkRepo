package ConfigAnnotation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import generic.ExcelUtility;

public class CreateContactTest extends BaseClass {
	
	
	@Test
	public void createContact() throws Exception {
		
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		List<List<String>> d = elib.getDataFromExcel("FlipKart");
		for(List<String>l:d) {
		System.out.println(l);
		}
		
		
		 
		
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		String ln = driver.findElement(By.id("dtlview_Last Name")).getText();
	     String header = driver.findElement(By.className("dvHeaderText")).getText();
	    
	    //verify contact header
	    if(header.contains(lastname))
	    {
	    	System.out.println(lastname+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(lastname+ "=======verification not succesfull=========");
	    }
	    
	    //verify organization name
	    if(ln.equals(lastname))
	    {
	    	System.out.println(lastname +"=" +ln);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
		/*Actions g=new Actions(driver);
		g.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@class='drop_down_usersettings'])[2]")).click();*/
	}
	
	@Test
	public void CreateContactWithDate() throws Throwable {
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		
	
		 
		
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		
		
		//enter all the details
		
		String startdate=jv.getSystemdateYYYYDDMM();
		System.out.println(startdate);
		
		
		String enddate = jv.getSystemdateYYYYDDMM(30);
		System.out.println(enddate);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
	
		
		//startdate
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		
		//enddate
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
		//save button
	    driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	    
	    String ln = driver.findElement(By.id("dtlview_Last Name")).getText();
	     String header = driver.findElement(By.className("dvHeaderText")).getText();
	     String actualsd = driver.findElement(By.id("dtlview_Support Start Date")).getText();
	     String actualed = driver.findElement(By.id("dtlview_Support End Date")).getText();
	    
	    //verify contact header
	    if(header.contains(lastname))
	    {
	    	System.out.println(lastname+ "=======verification succesfull=========");
	    }
	    
	    else
	    {
	    	System.out.println(lastname+ "=======verification not succesfull=========");
	    }
	    
	    //verify lastname
	    if(ln.equals(lastname))
	    {
	    	System.out.println(lastname +"=" +ln);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	    //verify startdate
	    if(actualsd.equals(startdate))
	    {
	    	System.out.println(actualsd +"=" +startdate);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	    
	    //verify endate
	    if(actualed.equals(enddate))
	    {
	    	System.out.println(actualed +"=" +enddate);
	    }
	    else
	    {
	    	System.out.println("==not matching==");
	    }
	}
	
	@Test
	public void CreateContactWithOrgTest() throws Throwable {
		ExcelUtility elib=new ExcelUtility();
		String lastname =elib.getDataFromExcel1("Sheet3", 1, 0)+jv.getRandomNumber();
		String organization  =elib.getDataFromExcel1("Sheet2", 1, 0)+jv.getRandomNumber();
		
		 
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
		
		
	Thread.sleep(4000);	
	driver.findElement(By.name("search_text")).sendKeys(organization);
	Thread.sleep(4000);	
	driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
	
	//driver.findElement(By.xpath("//a[@text()='"+organization+"']")).click();
	Thread.sleep(4000);	
	driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
	
	//switch to parent window
	wb.switchToTabonURL(driver, "Contacts&action");
	
	
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
	 
	}
}


