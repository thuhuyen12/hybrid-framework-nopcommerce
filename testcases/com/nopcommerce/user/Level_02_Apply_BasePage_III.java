package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_III extends BasePage {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir"); //lấy ra thư mục của dự án
	String emailAddress;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		emailAddress = "nhatminh" + generateFakeNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		waitForElementClickable(driver, "//button[@id ='register-button']");
		clickToElement(driver, "//button[@id ='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Le");
		sendkeyToElement(driver, "//input[@id='LastName']", "Thu Huyen");
		sendkeyToElement(driver, "//input[@id='Email']", "thuhuyen@456");
		sendkeyToElement(driver, "//input[@id='Password']", "1234567");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "1234567");
		
		waitForElementClickable(driver, "//button[@id ='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class, 'message-error')]//ul//li"), "Wrong email");
	}
	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Le");
		sendkeyToElement(driver, "//input[@id='LastName']", "Thu Huyen");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "1234567");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "1234567");
		
		waitForElementClickable(driver, "//button[@id ='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	
		waitForElementClickable(driver, "//a[@class ='ico-logout']");
		clickToElement(driver, "//a[@class ='ico-logout']");
	}

	@Test
	public void TC_04_Register_Existed_Email() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Le");
		sendkeyToElement(driver, "//input[@id='LastName']", "Nhat Minh");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "1234567");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "1234567");
		
		waitForElementClickable(driver, "//button[@id ='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}
		
	@Test
	public void TC_05_Register_Password_LessThan_6_Chars() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Le");
		sendkeyToElement(driver, "//input[@id='LastName']", "Nhat Minh");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "12345");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");

		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}	
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		waitForElementClickable(driver, "//a[@class ='ico-register']");
		clickToElement(driver, "//a[@class ='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Le");
		sendkeyToElement(driver, "//input[@id='LastName']", "Nhat Minh");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "12345");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public int generateFakeNumber() {
		Random randomNumber = new Random();
		return randomNumber.nextInt(9999);
	}
		
		

}
