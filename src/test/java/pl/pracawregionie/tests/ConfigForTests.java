package pl.pracawregionie.tests;

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

public class ConfigForTests {

	public ConfigForTests() throws Exception {

	}

	protected WebDriver driver;
	protected String basicURL = "http://dev:dev@stage.pracawregionie.pl/";
	protected ChromeOptions chromeOptions;

	protected String userName = this.readRandomNameFromFile();
	protected String userSurname = this.readRandomSurnameFromFile();
	protected String userLogin = userName + userSurname;
	protected int gender = this.genderCheck();
	protected String gender2 = this.genderCheck2();
	protected final String PASSWORD = "QWEqwe123";
	protected final String EMAIL_FIRST = "testspwr+";
	protected final String EMAIL_END = "@gmail.com";
	protected final String FULL_EMAIL = EMAIL_FIRST + userLogin + EMAIL_END;

	protected void beforeTest() {
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized", "--disable-extensions");
		driver = new ChromeDriver(chromeOptions);
		driver.get(basicURL);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	protected void afterTest() {
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
			gender = 0;
		}
		gender = 1;
		return gender;
	}
	
	protected String genderCheck2() {
		if (userName.length() - 1 == 'a') {
			@SuppressWarnings("unused")
			String gender = "Pani";
		}
		String gender = "Pan";
		return gender;
	}

}
