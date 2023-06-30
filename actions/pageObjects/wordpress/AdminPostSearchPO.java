package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchPageUI;
import pageUIs.wordpress.UserHomePageUI;

public class AdminPostSearchPO extends BasePage {
	private WebDriver driver;
	
	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return WordpressPageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickToSearchPostButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isPostSearchTableDisplayedInTable(String columnName, String cellValue) {
		int headerIndex = getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_COLUMN_NAME, columnName);
		waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue );
		return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
	}

	public AdminPostAddNewPO clickToPostTitleInTable(String columnName, String cellValue) {
		int headerIndex = getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_COLUMN_NAME, columnName);
		waitForElementClickable(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		clickToElement(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		return WordpressPageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void clickSelectCheckboxInTable(String newPostTitle) {
		waitForElementClickable(driver, AdminPostSearchPageUI.CHECKBOX_BY_POST_TITLE, newPostTitle);
		clickToElement(driver,  AdminPostSearchPageUI.CHECKBOX_BY_POST_TITLE, newPostTitle);
	}

	public void selectActionInDropdown(String value) {
		waitForElementVisible(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, value);
		selectItemInDefaultDropdown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, value);
	}

	public void clickToApplyButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		
	}

	public boolean isPostTitleMovedToTrash(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.MESSAGE_POST_TITLE_MOVE_TO_TRASH, message);
		return isElementDisplayed(driver,AdminPostSearchPageUI.MESSAGE_POST_TITLE_MOVE_TO_TRASH, message);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, message);
		return isElementDisplayed(driver,AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, message);
		
	}

	
	
	
	
}
