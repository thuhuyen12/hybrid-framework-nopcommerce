package pageObjects.jquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.HomePageUIsJQuery;

public class HomePageObjectJQuery extends BasePage{
	WebDriver driver;
	
	public HomePageObjectJQuery(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUIsJQuery.PAGINGATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUIsJQuery.PAGINGATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		waitForElementVisible(driver, HomePageUIsJQuery.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendkeyToElement(driver, HomePageUIsJQuery.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		pressKeyToElement(driver, HomePageUIsJQuery.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}

	public boolean isPageNumberActived(String pageNumber) {
		waitForElementVisible(driver, HomePageUIsJQuery.PAGINGATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUIsJQuery.PAGINGATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
	}


	public List getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUIsJQuery.TOTAL_PAGINATION);
		System.out.println("Total size = "+ totalPage);
		
		List<String> allRowsValueAllPage = new ArrayList<String>();
		//Duyệt qua tất cả các page number (paging)
		for (int index = 1; index <= totalPage; index++) {
			clickToElement(driver, HomePageUIsJQuery.PAGINATION_INDEX, String.valueOf(index) );
			
			//Get text của tất cả row mỗi page đưa vào List allRowsValueAllPage
			List<WebElement> allRowElementEachPage = getListElements(driver, HomePageUIsJQuery.ALL_ROW_COUNTRY_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowsValueAllPage.add(eachRow.getText());
				
			}
		}
		
	//In ra tất cả giá trị row của tất cả các page
	for (String value : allRowsValueAllPage) {
		System.out.println(value);
	}
		
		
		return allRowsValueAllPage;
	
	}

	public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
		//Lấy ra column Index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUIsJQuery.COLUMN_INDEX_BY_NAME, columnName) +1 ;

		//Sendkeys vào row Number nào
		waitForElementVisible(driver, HomePageUIsJQuery.ROW_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUIsJQuery.ROW_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowNumber,String.valueOf(columnIndex));
	
	}

	public void selectDropdownByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
		int columnIndex = getElementSize(driver, HomePageUIsJQuery.COLUMN_INDEX_BY_NAME, columnName) +1 ;
		//Select dropdown
		waitForElementClickable(driver, HomePageUIsJQuery.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUIsJQuery.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX,value, rowNumber, String.valueOf(columnIndex));
		
	}

	public void clickLoadDataButton() {
		waitForElementClickable(driver, HomePageUIsJQuery.LOAD_DATA_BUTTON);
		clickToElement(driver, HomePageUIsJQuery.LOAD_DATA_BUTTON);
		
	}

	public void clickToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUIsJQuery.COLUMN_INDEX_BY_NAME, columnName) +1 ;
		
		waitForElementClickable(driver, HomePageUIsJQuery.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		clickToElement(driver, HomePageUIsJQuery.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
	//Hoặc dùng checkToDefaultCheckbox....
	}

	public void clickToIconByRowNumber(String rowNumber, String iconName) {
		waitForElementClickable(driver, HomePageUIsJQuery.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, iconName);
		clickToElement(driver, HomePageUIsJQuery.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, iconName);

		
	}
	
	
	
	
	
	
	
	
	
	
	
}
