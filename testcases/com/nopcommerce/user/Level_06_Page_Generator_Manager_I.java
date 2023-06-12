package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

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

public class Level_06_Page_Generator_Manager_I extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		//Map driver với driver bên BaseTest
		driver = getBrowserDriver(browserName);
		
		firstName="Nguyen";
		lastName="Minh Tai";
		password="123456";
		validEmail = "minhtai" + generateFakeNumber() + "@gmail.com";
		inValidEmail = "minhtai" + generateFakeNumber() + "@123@345";
		emailNotFound = "behuyen" + generateFakeNumber() + "@mail.vn";
		
		homePage = new HomePageObject(driver);
		
		//Pre-condition: Successful register
		System.out.println("Pre-condition - Step 1: Click to Register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);

		System.out.println("Pre-condition - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Pre-condition - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Pre-condition - Step 4: Verrify messages register success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
//		System.out.println("Register page - Step 5: Click to Logout link");
//		registerPage.clickToLogoutLink();
		//Sau khi logout quay về home => khởi tạo home lên
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login - Step 1: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login - Step 2: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login - Step 3: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(inValidEmail);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(emailNotFound);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Existed_Email_Empty_Password() {
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(validEmail);
		
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existed_Email_Invalid_Password() {
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox("12345thu");

		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Success_Login() {
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private String firstName, lastName, validEmail, inValidEmail, emailNotFound,  password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	

}
