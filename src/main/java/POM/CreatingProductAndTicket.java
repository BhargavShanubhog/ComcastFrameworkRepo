package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingProductAndTicket {
	WebDriver driver;
	public CreatingProductAndTicket(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
	private WebElement jj;
	
	public WebElement getJj() {
		return jj;
	}

}
