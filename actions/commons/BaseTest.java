package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
		} else if(browserName.equals("coccoc")) {
			//Cốc cốc driver trừ đi 5 - 6 version ra chromedriver
				WebDriverManager.chromiumdriver().driverVersion("112.0.5615.49").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
				driver = new ChromeDriver(options);
		} else if (browserName.equals("brave")) {
			//Brave browser version nào dùng chromedriver version đó
			WebDriverManager.chromedriver().driverVersion("113.0.5672.63").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
	} else {
				throw new RuntimeException("Browser name invalid");
			}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.USER_PAGE_URL);
		return driver;
	}
	
	
	protected WebDriver getBrowserDriver(String browserName, String appURL) {
		
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
		} else if(browserName.equals("coccoc")) {
			//Cốc cốc driver trừ đi 5 - 6 version ra chromedriver
				WebDriverManager.chromiumdriver().driverVersion("112.0.5615.49").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
				driver = new ChromeDriver(options);
		} else if (browserName.equals("brave")) {
			//Brave browser version nào dùng chromedriver version đó
			WebDriverManager.chromedriver().driverVersion("113.0.5672.63").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
	} else {
				throw new RuntimeException("Browser name invalid");
			}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appURL);
		return driver;
	}
	
//		private String getEnvironmentURL(String serverName) {
//		
//		return null;
//	}


	protected int generateFakeNumber() {
		Random randomNumber = new Random();
		return randomNumber.nextInt(9999);
	}
	
	
	
}
	

