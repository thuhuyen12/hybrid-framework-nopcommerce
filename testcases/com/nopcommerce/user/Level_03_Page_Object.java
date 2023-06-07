package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object {
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		//Dữ liệu valid (hợp lệ)
		firstName="Nguyen";
		lastName="Minh Tai";
		password="123456";
		emailAddress = "minhtai" + generateFakeNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register page - Step 2: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register page - Step 3: Verrify error messages");
		Assert.assertEquals(registerPage.getErrorMessageAtFristnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {

		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register page - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("thuhuyen@456");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register page - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register page - Step 4: Verrify error messages for invalid email");
		Assert.assertEquals(registerPage.getInvalidEmailMessage(), "Wrong email");

	}
	@Test
	public void TC_03_Register_Success() {
		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register page - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		System.out.println("Register page - Step 4: Verrify messages register success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		Không có logout trên UI
//		System.out.println("Register page - Step 5: Click to Logout link");
//		registerPage.clickToLogoutLink();
	}

	@Test
	public void TC_04_Register_Existed_Email() {
		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register page - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register page - Step 4: Verrify messages error for existed email");
		Assert.assertEquals(registerPage.getErrorExistedEmailMessage(), "The specified email already exists");
	
	}
		
	@Test
	public void TC_05_Register_Password_LessThan_6_Chars() {
		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register page - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("1234");
		registerPage.inputToConfirmPasswordTextbox("1234");
		
		System.out.println("Register page - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register page - Step 4: Verrify password messages error less than 6 character");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}	
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Home page - Step 1: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register page - Step 2: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("0123");
		
		System.out.println("Register page - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 4: Verrify password messages error less than 6 character");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	
	}	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public int generateFakeNumber() {
		Random randomNumber = new Random();
		return randomNumber.nextInt(9999);
	}
	
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir"); //lấy ra thư mục của dự án
	private String firstName, lastName, emailAddress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	

}
