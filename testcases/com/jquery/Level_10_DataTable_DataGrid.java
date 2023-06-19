package com.jquery;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.jqueryDataTable.HomePageObjectJQuery;
import pageObjects.jqueryDataTable.PageGeneratorManagerJQuery;

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

public class Level_10_DataTable_DataGrid extends BaseTest{

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowserDriver(browserName, appURL);
		homePage =PageGeneratorManagerJQuery.getHomePage(driver);
	}

	//@Test
	public void Table_01() {
		homePage.openPagingByPageNumber("3");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("3"));

		homePage.openPagingByPageNumber("5");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("5"));

		homePage.openPagingByPageNumber("8");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("8"));

		homePage.openPagingByPageNumber("14");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("14"));

	}
	
	//@Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		homePage.enterToHeaderTextboxByLabel("Country", "Albania");
		homePage.enterToHeaderTextboxByLabel("Females", "24128");
		homePage.enterToHeaderTextboxByLabel("Males", "25266");
		homePage.enterToHeaderTextboxByLabel("Total", "49397");
		homePage.sleepInSecond(3);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
		homePage.enterToHeaderTextboxByLabel("Females", "276880");
		homePage.enterToHeaderTextboxByLabel("Males", "276472");
		homePage.enterToHeaderTextboxByLabel("Total", "553353");		
	}
	
//	@Test
	public void Table_03_Enter_To_Header() {
		//Lấy data từ file đã lưu => Verify
		
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	@Test
	public void Table_04_Action_At_Any_Row() {	
		homePage.clickLoadDataButton();
		
		//Column name: tại cột nào - tham số 1
		//Row number: tại row nào - tham số 2
		//Data để nhập liệu - tham số 3
//		homePage.enterToTextboxByColumnNameAtRowNumber("Company", "2","Nha Gon");
//		homePage.sleepInSecond(2);
//
//		homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "1","Nhat Minh");
//		homePage.sleepInSecond(2);
//
//		homePage.enterToTextboxByColumnNameAtRowNumber("Order Placed", "3","Chicken");
//		homePage.sleepInSecond(2);
//
//		homePage.selectDropdownByColumnNameAtRowNumber("Country", "3","Japan");
//		homePage.sleepInSecond(2);
//
//		homePage.clickToCheckboxByColumnNameAtRowNumber("NPO?", "4"); //Bỏ chọn
//		homePage.sleepInSecond(2);
//
//		homePage.clickToCheckboxByColumnNameAtRowNumber("NPO?", "2"); //Chọn
//		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("7", "Insert Row Above");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("5", "Move Up");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("2", "Move Down");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("6", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("7", "Remove Current Row");

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObjectJQuery homePage;
	List<String> expectedAllCountryValues;
	List<String> actualAllCountryValues;
}
