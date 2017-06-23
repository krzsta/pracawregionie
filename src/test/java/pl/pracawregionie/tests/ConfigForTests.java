package pl.pracawregionie.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConfigForTests {
	
	protected WebDriver driver;
	protected String basicURL = "http://dev:dev@stage.pracawregionie.pl/";
	protected ChromeOptions chromeOptions;
	
	protected String userName = "lucja";
	protected final String PASSWORD = "QWEqwe123";
	protected final String EMAIL_FIRST = "testspwr+";
	protected final String EMAIL_END = "@gmail.com";
	protected final String FULL_EMAIL = EMAIL_FIRST + userName + EMAIL_END;
		
	protected void beforeTest(){		
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized", "--disable-extensions");
		driver = new ChromeDriver(chromeOptions);
		driver.get(basicURL);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	}

	protected void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
