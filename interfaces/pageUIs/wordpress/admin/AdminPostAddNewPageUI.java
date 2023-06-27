package pageUIs.wordpress.admin;

public class AdminPostAddNewPageUI {

	public static final String TITLE_BLOCK = "css=h1.wp-block-post-title";
	
	public static final String BODY_BLOCK_BEFORE_CLICK = "css=p.block-editor-default-block-appender__content";

	public static final String BODY_BLOCK_AFTER_CLICK = "xpath=//p[@data-title='Paragraph']";

	public static final String PUBLISH_BUTTON = "css=button.editor-post-publish-button";

	public static final String POST_PUBLISHED_MESSAGE ="xpath=//div[@class='components-snackbar__content' and text()='%s']";
}
