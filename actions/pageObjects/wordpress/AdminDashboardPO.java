package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {

	private WebDriver driver;
	
	public AdminDashboardPO(WebDriver driver) {
		this.driver= driver;
	}

	public AdminPostSearchPO clickToPostLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.POST_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_LINK);
		return new AdminPostSearchPO(driver);
	}
	
	
	
	
}
