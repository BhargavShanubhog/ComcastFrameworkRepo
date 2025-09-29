package Baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;

import POM.Homepage;
import POM.LoginPage;
import generic.DataBaseUtility;
import generic.FileUtility;
import generic.JavaUtility;
import generic.UtilityClassObjects;
import generic.WebDriverUtility;

public class BaseClass {
	public FileUtility flib=new FileUtility();
	public JavaUtility jv=new JavaUtility();
	public WebDriverUtility wb=new WebDriverUtility();
	public DataBaseUtility db=new DataBaseUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS() {
		System.out.println("Connect to DB, Report Config");
		
		
	}
	
	/*@Parameters("BROWSER")
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC(String browser) throws Throwable {
		System.out.println("launch the browser");
		String BROWSER =browser;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
	}*/
	
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("launch the browser");
		String BROWSER = System.getProperty("browser",flib.getDataFromProperties("browser"));
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObjects.setDriver(driver);
		
	}
	
	
		
	
	
		
	
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("Login");
		String URL = System.getProperty("url",flib.getDataFromProperties("url"));
		String USERNAME = System.getProperty("username",flib.getDataFromProperties("username"));
		String PASSWORD = System.getProperty("password",flib.getDataFromProperties("password"));
         driver.get(URL);
		
		driver.manage().window().maximize();
		wb.waitforPageLoad(driver);
		LoginPage ll=new LoginPage(driver);
		ll.loginToApp(USERNAME, PASSWORD);
	}
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM() throws Throwable {
		System.out.println("Logout");
		Homepage l2=new Homepage(driver);
		l2.SignOut();
	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("close the browser");
		driver.quit();
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS() {
		System.out.println("close DB, report config");
		
	}

	
	

}
