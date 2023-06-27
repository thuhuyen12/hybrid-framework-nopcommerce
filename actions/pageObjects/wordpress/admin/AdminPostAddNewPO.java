package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {

private WebDriver driver;
	
	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToTitleBlock(String title) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_BLOCK);
		sendkeyToElement(driver, AdminPostAddNewPageUI.TITLE_BLOCK, title);
	}

	public void clickToBodyBlock() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BLOCK_BEFORE_CLICK);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BLOCK_BEFORE_CLICK);
	}

	public void enterToBodyBlock(String body) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_BLOCK_AFTER_CLICK);
		sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_BLOCK_AFTER_CLICK, body);
	}

	public void clickToPublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
	}

	public boolean isPostPublishedMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_PUBLISHED_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.POST_PUBLISHED_MESSAGE, message);
	}

	public AdminPostSearchPO openPostSearchPage(String postSearchUrl) {
		openPageUrl(driver, postSearchUrl);
		return new AdminPostSearchPO(driver);
	}
}
