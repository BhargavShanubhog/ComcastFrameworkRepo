package POM;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//Rule1:- create a seperate java class
public class LoginPage {
	WebDriver driver;
	//Rule2:-object creation
	@FindBy(xpath="//input[@name='user_name']")
	WebElement un;
	
	@FindBy(name="user_password")
	WebElement pwd;
	
	@FindBy(id="submitButton")
	WebElement loginBtn;
	
	//Rule3:- object initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	//Rule4:- object encapsulation
	public WebElement getUn() {
		return un;
	}

	public WebElement getPwd() {
		return pwd;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule5:- object utilisation
	public void loginToApp(String username, String password) throws InterruptedException {
		Thread.sleep(2000);
		un.sendKeys(username);
		pwd.sendKeys(password);
		loginBtn.click();
		
	}
	
	
	
	
	

}
