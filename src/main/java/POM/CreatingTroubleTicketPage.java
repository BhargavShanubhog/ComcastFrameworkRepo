package POM;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatingTroubleTicketPage {
	WebDriver driver;
	public CreatingTroubleTicketPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Trouble Tickets")
	private WebElement tt;
	
	@FindBy(name="ticket_title")
	private WebElement title;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement jj;
	
	@FindBy(name="search_field")
	private WebElement sf;
	
	@FindBy(className="txtBox")
	private WebElement tx;
	
	@FindBy(name="submit")
	private WebElement button;
	
	@FindBy(xpath="(//img[@src='themes/softed/images/select.gif'])[2]")
	private WebElement product;
	
	@FindBy(name="search_text")
	private WebElement pname;
	
	@FindBy(xpath="//input[@value='Search Now'")
	private WebElement sn;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement pr;
	
	@FindBy(name="search_text")
	private WebElement product1;
	
	@FindBy(name="search")
	private WebElement search;
	
	@FindBy(name="search_field")
	private WebElement dd;
	

	public WebElement getProduct() {
		return product;
	}

	public WebElement getDd() {
		return dd;
	}

	public WebElement getSearch() {
		return search;
	}

	public WebElement getProduct1() {
		return product1;
	}

	public WebElement getPr() {
		return pr;
	}

	public WebElement getTt() {
		return tt;
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getJj() {
		return jj;
	}

	public WebElement getSf() {
		return sf;
	}

	public WebElement getTx() {
		return tx;
	}

	public WebElement getButton() {
		return button;
	}
	
	public void Switch() {
		String parentWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String childWindowHandle : allWindowHandles) {
	        if (!childWindowHandle.equals(parentWindowHandle)) {
	            driver.switchTo().window(childWindowHandle);
	           
	            break; 
	        }
	    }
	}
	
	

}
