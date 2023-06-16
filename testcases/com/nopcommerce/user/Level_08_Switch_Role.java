package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.admin.DashboardPageObject;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.MyProductReviewPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Switch_Role extends BaseTest{

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName="Nguyen";
		lastName="Minh Tai";
		password="123456";
		validEmail = "minhtai" + generateFakeNumber() + "@gmail.com";		
		adminEmail = "admin@yourstore.com";
		adminPassword= "admin";
		
		homePage = PageGeneratorManager.getHomePage(driver);

		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
	}

	@Test
	public void User_01_User_To_Admin() {
		loginPage = homePage.clickToLoginLink();	
		
		//Login with role User
		homePage = loginPage.loginAsUser(validEmail,password);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		homePage = loginPage.clickLogoutAtUserPage(driver);
		
		homePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
	
		dashboardPage= adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
		//Logout => Admin Login
		adminLoginPage = dashboardPage.clickLogoutAtAdminPage(driver);
		
	}
	
	@Test
	public void User_02_Admin_To_User() {
		//Từ admin => User homepage
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		//Homepage => login page user
		loginPage = homePage.clickToLoginLink();
		
		homePage = loginPage.loginAsUser(validEmail,password);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, validEmail, password, adminEmail, adminPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private AdminLoginPageObject adminLoginPage;
	private DashboardPageObject dashboardPage;
	
}
