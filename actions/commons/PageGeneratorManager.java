package commons;

import org.openqa.selenium.WebDriver;

import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.admin.DashboardPageObject;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.MyProductReviewPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;

public class PageGeneratorManager {
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerInfoPageObject getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPageObject(driver);
	}
	
	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	public static MyProductReviewPageObject getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}
	
	public static RewardPointPageObject getRewardPointPage(WebDriver driver) {
		return new RewardPointPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
}
