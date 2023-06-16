package pageUIs.jquery;

public class HomePageUIsJQuery {
	public static final String PAGINGATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
	
	public static final String PAGINGATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
	
	public static final String TOTAL_PAGINATION ="css=ul.qgrd-pagination-ul>li.qgrd-pagination-page";
	
	public static final String PAGINATION_INDEX ="xpath=//ul[@class='qgrd-pagination-ul']/li[@class= 'qgrd-pagination-page'][%s]/a";
	
	public static final String ALL_ROW_EACH_PAGE ="xpath=//tbody/tr";
	
	public static final String ALL_ROW_COUNTRY_EACH_PAGE ="xpath=//tbody/tr/td[@data-key='country']";

	//Index của cột cần enter/ click/ select vào
	public static final String COLUMN_INDEX_BY_NAME ="xpath=//tr/th[text()='%s']/preceding-sibling::th";
	
	public static final String ROW_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX ="xpath=//tbody/tr[%s]/td[%s]/input";
	
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX="xpath=//tbody/tr[%s]/td[%s]//div/select";

	public static final String LOAD_DATA_BUTTON ="xpath=//button[@id='load']";
	
	public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX ="xpath=//label[@class='checkbox']/input[@id='tblAppendGrid_isNPO_%s']";
	//hoặc dùng xpath=//tbody/tr[3]/td[5]//input[@type='checkbox']
	public static final String ICON_BY_COLUMN_INDEX_AND_ROW_INDEX ="xpath=//tbody/tr[%s]//button[@title='%s']";

	
}
