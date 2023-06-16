package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
		//return new HomePageObject(driver);
	}

	public void inputToEmailTextbox(String inValidEmail) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, inValidEmail);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);	
	}
	
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}


	public String getUnsuccessfulErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESFUL_ERROR_MESSAGE);
	}

	public HomePageObject loginAsUser(String validEmail, String password) {
		//Wrapper từ những hàm khác khi thực hiện các action
		inputToEmailTextbox(validEmail);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
	
	
	

	
	
	
	
}
