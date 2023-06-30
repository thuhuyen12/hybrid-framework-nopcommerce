package pageUIs.wordpress;

public class UserHomePageUI {
	public static final String POST_TITLE ="xpath=//article//h2//a[text()='%s']";
	
	public static final String POST_BODY ="xpath=//article//div[@class='entry-content']//p[text()='%s']";

	public static final String AUTHOR_BY_POST_TITLE ="xpath=//article//h2//a[text()='%s']//parent::h2//following-sibling::div//span[@class='author vcard']/a[text()='%s']";

	public static final String DATE_OF_POST_BY_POST_TITLE ="xpath=//article//h2//a[text()='%s']//parent::h2//following-sibling::div//span[@class='posted-on']/a/time[text()='%s']";

	public static final String SEARCH_TEXTBOX = "xpath=//aside[@id='secondary']//input[@type='search']";

	public static final String SEARCH_BUTTON = "xpath=//aside[@id='secondary']//input[@type='submit']";

	public static final String NOTHING_FOUND_MESSAGE ="css=h1.page-title";

}
