package generic;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitforPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	}
	
	public void waitforElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitforElementClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void switchToTabonURL(WebDriver driver,String partialURL) {
		Set<String>set=driver.getWindowHandles();
		Iterator<String>it=set.iterator();
		while(it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			String actURL=driver.getCurrentUrl();
			if(actURL.contains(partialURL)) {
				break;
			}
		}
	}
	
	public void switchToTabonTitle(WebDriver driver,String partialTitle) {
		Set<String>set=driver.getWindowHandles();
		Iterator<String>it=set.iterator();
		while(it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			String actURL=driver.getTitle();
			if(actURL.contains(partialTitle)) {
				break;
			}
		}
	}
	
	public void switchToFrame(WebDriver driver,int Index) {
		driver.switchTo().frame(Index);
	}
	
	public void switchToFrame(WebDriver driver,String Name) {
		driver.switchTo().frame(Name);
	}
	public void switchToFrame(WebDriver driver,WebElement Index) {
		driver.switchTo().frame(Index);
	}
	
	public void switchToAlertandAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertandDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element,int text) {
		Select sel=new Select(element);
		sel.selectByIndex(text);
	}
	
	public void mousemoveonElement(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	
	
	

}
