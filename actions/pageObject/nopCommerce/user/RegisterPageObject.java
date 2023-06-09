package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
import org.testng.annotations.Test;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage{

	public RegisterPageObject(WebDriver driver) {
		this.driver= driver;
	}
	private WebDriver driver;
	
	@Step("Click to register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON );
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	
	public String getErrorMessageAtFristnameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);	
		return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);	
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);		
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Enter to first name textbox with value is {0}")
	public void inputToFirstnameTextbox(String firstname) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstname);
		
	}

	@Step("Enter to last name textbox with value is {0}")
	public void inputToLastnameTextbox(String lastname) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastname);
	}

	@Step("Enter to email textbox with value is {0}")
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
		
	}

	@Step("Enter to password textbox with value is {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}
	@Step("Enter to confirm password textbox with value is {0}")
	public void inputToConfirmPasswordTextbox(String confirmpassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmpassword);		
	}

	@Step("Verify register success message")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);	
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}

	public String getErrorExistedEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);		
		return getElementText(driver, RegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
	}

	public String getInvalidEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.INVALID_EMAIL_MESSAGE);		
		return getElementText(driver, RegisterPageUI.INVALID_EMAIL_MESSAGE);
	}

}
