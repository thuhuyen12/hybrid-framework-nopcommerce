package pageObject.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import facebook.pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailAddressTextboxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isRe_enterEmailTextboxUnDisplayed() {
		return isElementUndisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}

	public boolean isRe_enterEmailTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}

	public void clickCloseIcon() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_ICON);
	}

	public boolean isEmailAddressTextboxUndisplayed() {
		waitForElementUndisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}









}
