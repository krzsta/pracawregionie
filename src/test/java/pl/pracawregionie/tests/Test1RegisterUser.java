package pl.pracawregionie.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accounts.google.gmail.GoogleEmailAccount;
import com.accounts.google.gmail.GoogleEmailAccountLoginPage;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pl.pracawregionie.configs.ConfigForTests2;
import pl.pracawregionie.pages.MainPage;
import pl.pracawregionie.pages.RegistrationUserPage;

public class Test1RegisterUser extends ConfigForTests2 {

	public Test1RegisterUser() throws Exception {
		super();
	}

	@BeforeTest
	public void beforeTest() {
		super.beforeTest();
	}

	@Test(priority = 1)
	public void openMainPage() {
		test = extent.createTest("Open browser and navigate to page");
		/*
		test.log(Status.INFO, "Open browser");
		test.log(Status.INFO, "Navigate to www.stage.pracawregionie.pl");
		test.log(Status.INFO, "Assert url");
		*/
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl11111/");
	}

	@Test(priority = 2)
	public void navigationToRegisterNewUserPage() {
		test = extent.createTest("Navigate to user register page");
		
		MainPage mainPage = new MainPage(driver);
		mainPage.navigateToRegisterNewUserPage();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/user/auth/registration");
	}

	@Test(priority = 3)
	public void registerNewUser() {
		test = extent.createTest("Register new User");
		
		RegistrationUserPage newUser = new RegistrationUserPage(driver);
		newUser.registerNewUser(FULL_EMAIL, PASSWORD, gender);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success template']")).isDisplayed());
		Assert.assertTrue(driver.getPageSource().contains(
				"Zarejestrowano pomyślnie. Na podany adres email została wysłana wiadomość z linkiem aktywacyjnym."));
		Assert.assertEquals(driver.getCurrentUrl(), "http://stage.pracawregionie.pl/user/auth/login");
	}

	@Test(priority = 4)
	public void loginToEmailAccountAndConfirmRegistration() {
		test = extent.createTest("Login to email account and confirm register");
		
		GoogleEmailAccountLoginPage emailAccount = new GoogleEmailAccountLoginPage(driver);
		emailAccount.navigateToEmailAccountAndLogIn();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");

		GoogleEmailAccount accountUser = new GoogleEmailAccount(driver);
		accountUser.confirmRegistration();
	}

	@Test(priority = 5)
	public void loginToAccount() {
		test = extent.createTest("Login to acount on www.stage.pracawregionie.pl");
		
		driver.get(basicURL);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/");

		MainPage mainPage = new MainPage(driver);
		mainPage.loginToAccount(FULL_EMAIL, PASSWORD);

		Assert.assertTrue(driver.findElement(By.id("profile")).isDisplayed());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test case Failed due to below issues",
					ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test case PASSED", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
