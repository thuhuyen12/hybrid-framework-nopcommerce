package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageObject extends BasePageFactory {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //Khởi tạo, tìm element trong class này 
		//Nên UI (locate element) + action phải ở chung 1 class
	}
	
	//Page UI
	@FindBy(how = How.XPATH, using = "//a[@class ='ico-register']")
	private WebElement registerLink;
	
	@FindBy(xpath = "//a[@class ='ico-login']")
	private WebElement loginLink;
	
	@FindBy(xpath = "//a[@class ='ico-account']")
	private WebElement myAccountLink;

	//Page Object/ action

	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink );
	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
	}
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, myAccountLink);	
		return isElementDisplayed(driver, myAccountLink);
	}













}
