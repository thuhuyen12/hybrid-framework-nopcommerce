package pageObjects.sourceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sourceLab.LoginPageUIs;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUIs.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUIs.USERNAME_TEXTBOX, userName);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUIs.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUIs.PASSWORD_TEXTBOX, password);
		
	}

	public ProductPageObject clickLoginButton() {
		waitForElementClickable(driver, LoginPageUIs.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUIs.LOGIN_BUTTON);
		return SourceLabPageGeneratorManager.getProductPage(driver);
	}
}
