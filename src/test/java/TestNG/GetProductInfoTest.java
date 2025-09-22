package TestNG;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.ExcelUtility;
import generic.JsonUtility;

public class GetProductInfoTest {
	@Test(dataProvider="getData")
	public void createContactTest(String brandName, String productName) throws Throwable {
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
		
		driver.get("https://amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		//String x="//span[text()='"+productName+"']/../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]/span[2]";
		List<WebElement> x=driver.findElements(By.xpath("//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']"));
		for(WebElement Y:x) {
			if(Y.getText().equals(productName)) {
				System.out.println(Y.getText());
			}
			
		}
		
	
	
		
		driver.quit();
		}
	
	@DataProvider
	public Object[][] getData() throws Throwable, Exception{
		ExcelUtility elib=new ExcelUtility();
		int rowCount=elib.getRowCount("Sheet4");
		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
		
		objArr[i][0]=elib.getDataFromExcel1("Sheet4", i+1, 1);
		objArr[i][1]=elib.getDataFromExcel1("Sheet4", i+1, 2);
		
		
	
		
		}
		return objArr;
}}
