package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {

	private WebDriver driver;
	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isPostTitleDisplayedOnHomePage(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE, postTitle);
		return isElementDisplayed(driver, UserHomePageUI.POST_TITLE, postTitle);
	}
	public boolean isPostBodyDisplayedOnHomePage(String postBody) {
		waitForElementVisible(driver, UserHomePageUI.POST_BODY, postBody);
		return isElementDisplayed(driver, UserHomePageUI.POST_BODY, postBody);
	}
	public boolean isAuthorDisplayedOnHomePage(String postTitle, String author) {
		waitForElementVisible(driver, UserHomePageUI.AUTHOR_BY_POST_TITLE, postTitle, author);
		return isElementDisplayed(driver, UserHomePageUI.AUTHOR_BY_POST_TITLE, postTitle, author);
	}
	public boolean isDateOfPostDisplayedOnHomePage(String postTitle, String dateOfPost) {
		waitForElementVisible(driver, UserHomePageUI.DATE_OF_POST_BY_POST_TITLE, postTitle, dateOfPost);
		return isElementDisplayed(driver, UserHomePageUI.DATE_OF_POST_BY_POST_TITLE, postTitle, dateOfPost);
		
	}
	public UserPostDetailPO clickToPostTitleOnHomePage(String postTitle) {
		waitForElementClickable(driver, UserHomePageUI.POST_TITLE, postTitle);
		clickToElement(driver, UserHomePageUI.POST_TITLE, postTitle);
		return WordpressPageGeneratorManager.getUserPostDetailPage(driver);
	}
	public void enterToSearchTextbox(String newPostTitle) {
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, newPostTitle);
	}
	public void clickToSearchButton() {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);	
	}
	public boolean isNothingFoundMessageDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.NOTHING_FOUND_MESSAGE);
		return isElementDisplayed(driver,UserHomePageUI.NOTHING_FOUND_MESSAGE);
		
	}
	
	
	
	
	
}