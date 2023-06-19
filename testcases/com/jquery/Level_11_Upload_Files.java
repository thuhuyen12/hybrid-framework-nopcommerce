package com.jquery;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.jqueryDataTable.HomePageObjectJQuery;
import pageObjects.jqueryDataTable.PageGeneratorManagerJQuery;
import pageObjects.jqueryUploadFiles.copy.HomePageObjectUploadFiles;
import pageObjects.jqueryUploadFiles.copy.PageGeneratorManagerUploadFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.RefreshPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_Upload_Files extends BaseTest{
	String hinh1 = "hinh1.jpg";
	String hinh2 = "hinh2.jpg";
	String hinh3 = "hinh3.jpg";
	String hinh4 = "hinh4.jpg";

	String[] multipleFileNames = {hinh1, hinh2, hinh3, hinh4};
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGeneratorManagerUploadFiles.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File() {
		//Load single file
		homePage.uploadMultipleFiles(driver, hinh1);
		//Verify file đã load lên thành công
		Assert.assertTrue(homePage.isFileLoadedByName(hinh1));
		//Click Start button từng file
		homePage.clickStartButtonAtEachFile();
		//Verify file đã upload thành công
		Assert.assertTrue(homePage.isFileUploadedByName(hinh1));
		//Verify link hình đc upload thành công (có chiều cao, chiều rộng)
		Assert.assertTrue(homePage.isFileLinkUploadedByName(hinh1));
	}
	
	@Test
	public void Upload_02_Multiple_File() {
		homePage.refreshCurrentPage(driver);
		homePage.sleepInSecond(2);
		
		homePage.uploadMultipleFiles(driver, multipleFileNames);
		Assert.assertTrue(homePage.isFileLoadedByName(hinh1));
		Assert.assertTrue(homePage.isFileLoadedByName(hinh2));
		Assert.assertTrue(homePage.isFileLoadedByName(hinh3));
		Assert.assertTrue(homePage.isFileLoadedByName(hinh4));

		homePage.clickStartButtonAtEachFile();
		Assert.assertTrue(homePage.isFileUploadedByName(hinh1));
		Assert.assertTrue(homePage.isFileUploadedByName(hinh2));
		Assert.assertTrue(homePage.isFileUploadedByName(hinh3));
		Assert.assertTrue(homePage.isFileUploadedByName(hinh4));

		Assert.assertTrue(homePage.isFileLinkUploadedByName(hinh1));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(hinh2));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(hinh3));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(hinh4));

	}
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObjectUploadFiles homePage;
	
}
