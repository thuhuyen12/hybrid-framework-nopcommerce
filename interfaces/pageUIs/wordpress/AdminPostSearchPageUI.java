package pageUIs.wordpress;

public class AdminPostSearchPageUI {

	public static final String ADD_NEW_BUTTON ="xpath=//a[@class='page-title-action' and text()='Add New']";
	
	public static final String SEARCH_TEXTBOX ="xpath=//input[@id='post-search-input']";
	
	public static final String SEARCH_POST_BUTTON ="xpath=//input[@id='search-submit']";
	
	public static final String TABLE_HEADER_INDEX_BY_COLUMN_NAME ="xpath=//thead//th[@id='%s']";
	
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX ="xpath=//tbody[@id='the-list']//td['%s']//a[text()='%s']";

	public static final String CHECKBOX_BY_POST_TITLE = "xpath=//th[@class='check-column']//label[contains(text(),'%s')]/following-sibling::input";

	public static final String ACTION_DROPDOWN = "xpath=//select[@name='action']";
	

	public static final String APPLY_BUTTON = "xpath=//input[@id='doaction']";
	
	public static final String MESSAGE_POST_TITLE_MOVE_TO_TRASH = "xpath=//div[@id='message']/p[text()='%s']";
	
	public static final String NO_POST_FOUND_MESSAGE = "xpath=//td[@class='colspanchange' and text()='%s']";

	
}

