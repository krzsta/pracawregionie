package pl.pracawregionie.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accounts.google.gmail.GoogleEmailAccountLoginPage;

import pl.pracawregionie.pages.MainPage;
import pl.pracawregionie.pages.RegistrationUserPage;

public class Test1RegisterUser extends ConfigForTests {

	@BeforeTest
	public void beforeTest() {
		super.beforeTest();
	}

	@Test(priority = 1)
	public void openMainPage() {
		Assert.assertEquals(driver.getTitle(), "Praca w Twoim regionie - pracawregionie.pl");
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pls/");		
	}

	@Test(priority = 2)
	public void navigationToRegisterNewUserPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.navigateToRegisterNewUserPage();
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/user/auth/registration");
	}

	@Test(priority = 3)
	public void registerNewUser() {
		RegistrationUserPage newUser = new RegistrationUserPage(driver);
		newUser.registerNewUser(FULL_EMAIL, PASSWORD);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success template']")).isDisplayed());
		Assert.assertTrue(driver.getPageSource().contains(
				"Zarejestrowano pomyślnie. Na podany adres email została wysłana wiadomość z linkiem aktywacyjnym."));
		Assert.assertEquals(driver.getCurrentUrl(), "http://stage.pracawregionie.pl/user/auth/login");
	}

	@Test(priority = 4)
	public void loginToEmailAccountAndConfirmRegistration() {
		GoogleEmailAccountLoginPage emailAccount = new GoogleEmailAccountLoginPage(driver);
		emailAccount.navigateToEmailAccountAndLogIn();
		Assert.assertEquals(driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");
	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
