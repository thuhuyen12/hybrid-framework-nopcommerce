package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_DRY {
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
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Le");
		driver.findElement(By.id("LastName")).sendKeys("Nhat Minh");
		driver.findElement(By.id("Email")).sendKeys("NhatMinh@123");
		driver.findElement(By.id("Password")).sendKeys("1234567");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("1234567");
		
		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "Wrong email");
	}
	@Test
	public void TC_03_Register_Success() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Le");
		driver.findElement(By.id("LastName")).sendKeys("Nhat Minh");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("1234567");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("1234567");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	}

	@Test
	public void TC_04_Register_Existed_Email() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Tran");
		driver.findElement(By.id("LastName")).sendKeys("Thu Huyen");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("1234567");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("1234567");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Password_LessThan_6_Chars() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Tran");
		driver.findElement(By.id("LastName")).sendKeys("Thu Huyen");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("12345");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Tran");
		driver.findElement(By.id("LastName")).sendKeys("Thu Huyen");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");

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
