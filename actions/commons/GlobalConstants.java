package commons;

import java.io.File;

public class GlobalConstants {
	public static final String USER_PAGE_URL= "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL= "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
	public static final String PROJECT_PATH = System.getProperty("user.div");
	public static final String OS_NAME = System.getProperty("os.name");

	
	//Window / linux/ mac: separator (tự động điền dấu / or \ tùy OS- hệ điều hành)
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";

	//Database / Account user, pass , port
	public static final String DP_DEV_URL = "192.244235.21"; //là 1 địa chỉ IP của thiết bị
	public static final String DP_DEV_USERNAME = "automation"; 
	public static final String DP_DEV_PASS = "11223@!da"; 

	public static final String DP_TEST_URL = "192.244235.213"; 
	public static final String DP_TEST_USERNAME = "huyenthu"; 
	public static final String DP_TEST_PASS = "11223@!da"; 

	public static final long SHORT_TIMEOUT = 5; 
	public static final long LONG_TIMEOUT = 30; 
	public static final long RETRY_TEST_FAIL = 3; 

	
	




}
