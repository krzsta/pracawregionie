package pl.pracawregionie.configs;

import pl.pracawregionie.Gender;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public abstract class ConfigBase {

    protected final String emailSuffix;
    protected String password;
    protected String emailPrefix;
    protected WebDriver driver;
    protected String basicURL;
    protected ChromeOptions chromeOptions;
    protected String userName;
    protected String userSurname;
    protected String userLogin;
    protected String fullEmail;
    protected Gender gender; // mozna zrzutowac do int jesli trzeba przez (int)gender
    protected String strGender;
    
    protected ExtentHtmlReporter htmlReporter;
	protected ExtentReports extent;
	protected ExtentTest test;

    public ConfigBase(String password, String emailPrefix, String emailSuffix, String basicURL) throws IOException {
        this.password = password;
        this.emailPrefix = emailPrefix;
        this.emailSuffix = emailSuffix;
        this.basicURL = basicURL;
        this.userName = readRandomFromFile("/src/test/resources/files/namesForEmail.csv");
        this.userSurname = readRandomFromFile("/src/test/resources/files/surnamesForEmail.csv");
        this.userLogin = userName + userSurname;
        this.fullEmail = emailPrefix + userLogin + emailSuffix;
        this.gender = this.userName.toLowerCase().endsWith("a") ? Gender.FEMALE : Gender.MALE;
        this.gender = this.userName.toLowerCase().endsWith("a") ? Gender.FEMALE2 : Gender.MALE2; //sprawdzić czy działa (poszukać lepszego rozwiązania)
        this.strGender = this.gender.toString();
    }

    @BeforeTest
    protected void beforeTest() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--disable-extensions");
        driver = new ChromeDriver(chromeOptions);
        driver.get(basicURL);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/reports/Report.html"); //dopisać datę do nazwy pliku
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

    @AfterTest
    protected void afterTest() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
    
	@AfterMethod
	public void afterMethod(ITestResult result) {
		//Przy takich małych operacjach z porównywaniem enumów najlepiej zawsze switch-case
		switch(result.getStatus()){
			case ITestResult.FAILURE:
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test case Failed due to below issues",
						ExtentColor.RED));
				test.fail(result.getThrowable());
				break;
			case ITestResult.SUCCESS:
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case PASSED", ExtentColor.GREEN));
				break;
			default:
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test case PASSED", ExtentColor.YELLOW));
				test.skip(result.getThrowable());
				break;
		}
	}

    private String readRandomFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();

        // try with resources == auto close stream, no need to use *.close()
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + path))) {
            for (String line; (line = reader.readLine()) != null; ) { //powinno dzialac ale nie kompiluje mi sie wiec mozliwe ze jednak nie ;)
                lines.add(line);
            }
        }

        return lines.size() > 0
                ? lines.get(new Random().nextInt(lines.size()))
                : "";
    }
}
