package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage{
	private WebDriver driver;
	
	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostTitleDisplayedOnPostDetailPage(String postTitle) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE, postTitle);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE, postTitle);
	}

	public boolean isPostBodyDisplayedOnPostDetailPage(String postBody) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY, postBody);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY, postBody);
	}

	public boolean isAuthorDisplayedOnPostDetailPage(String author) {
		waitForElementVisible(driver, UserPostDetailPageUI.AUTHOR, author);
		return isElementDisplayed(driver, UserPostDetailPageUI.AUTHOR, author);
	}

	public boolean isDateOfPostDisplayedOnPostDetailPage(String dateOfPost) {
		waitForElementVisible(driver, UserPostDetailPageUI.DATE_OF_POST, dateOfPost);
		return isElementDisplayed(driver, UserPostDetailPageUI.DATE_OF_POST, dateOfPost);
	}


	public AdminDashboardPO openAdminSite(WebDriver driver2, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return WordpressPageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	
	
	
}
