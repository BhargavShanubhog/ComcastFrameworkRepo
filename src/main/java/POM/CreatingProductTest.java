package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingProductTest {
	WebDriver driver;
	
	public CreatingProductTest(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="productname")
	private WebElement y;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement jj;
	
	@FindBy(name="sales_start_date")
	private WebElement sales_start;
	
	@FindBy(name="sales_end_date")
	private WebElement sales_end;
	
	@FindBy(xpath="//input[@name='start_date']")
	private WebElement support_start;
	
	@FindBy(xpath="//input[@name='expiry_date']")
	private WebElement support_end;
	
	

	public WebElement getSupport_start() {
		return support_start;
	}

	public WebElement getSupport_end() {
		return support_end;
	}

	public WebElement getSales_end() {
		return sales_end;
	}

	public WebElement getSales_start() {
		return sales_start;
	}

	public WebElement getY() {
		return y;
	}
	
	public WebElement getJj() {
		return jj;
	}

	

}
