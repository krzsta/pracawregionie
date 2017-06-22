package pl.pracawregionie.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/");
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

		Assert.assertTrue(newUser.categoriesCheckboxesAssert());
		Assert.assertTrue(newUser.voivodeshipsCheckboxesAssert());
	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
