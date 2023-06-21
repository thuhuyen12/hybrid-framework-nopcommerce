package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	// Hàm khởi tạo: trùng tên class + không có kiểu trả về
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	} 
	
	@Step("Navigate to Register page")
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
		//return new RegisterPageObject(driver);
	}
	
	@Step("Navigate to Login page")
	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	//Cách 2:	return new LoginPageObject(driver);
	}
	
	@Step("Verify 'My account' link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);	
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	
	@Step("Navigate to My account page")
	public CustomerInfoPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);	
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
}
