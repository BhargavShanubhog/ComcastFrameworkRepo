package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
	@FindBy(xpath="//input[@alt='Create Product..']")
	private WebElement createProductImgBtn;
	
	@FindBy(linkText="Products")
	private WebElement product;

}
