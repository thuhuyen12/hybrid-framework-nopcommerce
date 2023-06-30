package pageObjects.wordpress;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

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
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_PUBLISHED_AND_UPDATEED_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.POST_PUBLISHED_AND_UPDATEED_MESSAGE, message);
	}

	public AdminPostSearchPO openPostSearchPage(String postSearchUrl) {
		openPageUrl(driver, postSearchUrl);
		return WordpressPageGeneratorManager.getAdminPostSearchPage(driver);
	}

	public void editTheTitleOfPost(String newPostTitle) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_BLOCK);
		sendkeyToElement(driver, AdminPostAddNewPageUI.TITLE_BLOCK, newPostTitle);
	}

	public void editTheBodyOfPost(String newPostBody) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BLOCK_BEFORE_CLICK);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BLOCK_BEFORE_CLICK);
		clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.BODY_BLOCK_AFTER_CLICK);
		sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_BLOCK_AFTER_CLICK, newPostBody);
	}

	public void clickUpdateButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.UPDATE_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.UPDATE_BUTTON);
	}

	public boolean isPostUpdatedMessageDisplayed(String updatedMessage) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_PUBLISHED_AND_UPDATEED_MESSAGE, updatedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.POST_PUBLISHED_AND_UPDATEED_MESSAGE, updatedMessage);
	}
	
	
	
}
