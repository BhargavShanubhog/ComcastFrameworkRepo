package AutoHealing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPom {
	@FindBy(name="user_name")
	WebElement un;
	
	@FindBy(name="user_password")
	WebElement pwd;
	
	@FindAll({@FindBy(id="submitButton"), @FindBy(xpath="//input[@value='Login']")})
	private WebElement ele3;
	
	@Test
	public void sampleTest() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://49.249.28.218:8888/index.php?action=index&module=Home");
		SampleTestWithPom a=PageFactory.initElements(driver,SampleTestWithPom.class );
		a.un.sendKeys("admin");
		a.pwd.sendKeys("admin");
		a.ele3.click();
	}

}
