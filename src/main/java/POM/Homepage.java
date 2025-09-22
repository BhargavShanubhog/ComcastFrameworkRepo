package POM;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.WebDriverUtility;


public class Homepage {
	WebDriverUtility h=new WebDriverUtility();
	WebDriver driver;
	public Homepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement ss;
	
	@FindBy(linkText="Sign Out")
	private WebElement signout;
	
	@FindBy(linkText="Products")
	private WebElement pro;
	
	
	public WebElement getPro() {
		return pro;
	}

	public WebElement getOrgLink() {
		h.waitforElementClickable(driver, orgLink);
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public void SignOut() throws Throwable {
		Actions a=new Actions(driver);
		a.moveToElement(ss).perform();
		
		h.waitforElementPresent(driver, signout);
		signout.click();
	}
	

}
