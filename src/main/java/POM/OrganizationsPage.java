package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(name="search_text")
	private WebElement search;
	
	@FindBy(name="search_field")
	private WebElement search_f;
	
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgBtn;
	
	@FindBy(xpath="//input[@name='submit']")
	private WebElement r;
	
	public WebElement getSearch() {
		return search;
	}


	public WebElement getSearch_f() {
		return search_f;
	}
	
	
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	public WebElement getR() {
		return r;
	}
	
	

}
