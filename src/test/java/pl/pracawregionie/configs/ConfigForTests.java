package pl.pracawregionie.configs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public abstract class ConfigForTests {

	public ConfigForTests() throws Exception {

	}

	protected WebDriver driver;
	protected final String BASIC_URL = "http://dev:dev@stage.pracawregionie.pl/";
	protected ChromeOptions chromeOptions;

	protected String userName = readRandomNameFromFile();
	protected String userSurname = readRandomSurnameFromFile();
	protected String userLogin = userName + userSurname;
	protected int gender = genderCheck();
	protected String gender2 = genderCheck2();
	protected final String PASSWORD = "QWEqwe123";
	protected final String EMAIL_FIRST = "testspwr+";
	protected final String EMAIL_END = "@gmail.com";
	protected final String FULL_EMAIL = EMAIL_FIRST + userLogin + EMAIL_END;
	
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentReports extent;
	protected ExtentTest test;

	protected void beforeTest() {
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized", "--disable-extensions");
		driver = new ChromeDriver(chromeOptions);
		driver.get(BASIC_URL);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/reports/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Operating System", "Win10 Home Edition");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "STAGE");
		extent.setSystemInfo("Test author", "Krzysztof Stanowski");
		
		htmlReporter.config().setDocumentTitle("Regression tests");
		htmlReporter.config().setReportName("Regression tests");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	protected void afterTest() {
		extent.flush();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	protected String readRandomNameFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/src/test/resources/files/namesForEmail.csv"));
		String line = reader.readLine();
		List<String> lines = new ArrayList<String>();
		while (line != null) {
			lines.add(line);
			line = reader.readLine();
		}
		Random r = new Random();
		String randomName = lines.get(r.nextInt(lines.size()));
		reader.close();
		return randomName;
	}
	
	protected String readRandomSurnameFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/test/resources/files/surnamesForEmail.csv"));
		String line = reader.readLine();
		List<String> lines = new ArrayList<String>();
		while (line != null) {
			lines.add(line);
			line = reader.readLine();
		}
		Random r = new Random();
		String randomSurname = lines.get(r.nextInt(lines.size()));
		reader.close();
		return randomSurname;
	}

	protected int genderCheck() {
		if (userName.length() - 1 == 'a') {
			gender = 0; //female
		}
		gender = 1; //male
		return gender;
	}
	
	protected String genderCheck2() {
		if (userName.length() - 1 == 'a') {
			gender2 = "Pani";
		}
		gender2 = "Pan";
		return gender2;
	}

}