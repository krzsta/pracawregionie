package pl.pracawregionie.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.accounts.google.gmail.GoogleEmailAccount;
import com.accounts.google.gmail.GoogleEmailAccountLoginPage;

import pl.pracawregionie.configs.ConfigBase;
import pl.pracawregionie.pages.MainPage;
import pl.pracawregionie.pages.RegistrationEmplyerPage;

public class Test2RegisterEmployer extends ConfigBase {

	public Test2RegisterEmployer() throws Exception {
		super("QWEqwe123", "testspwr+", "@gmail.com", "http://dev:dev@stage.pracawregionie.pl/");
	}

	@Test(priority = 1)
	public void openMainPage() {
		Assert.assertEquals(driver.getTitle(), "Praca w Twoim regionie - pracawregionie.pl");
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/");
	}

	@Test(priority = 2)
	public void navigationToRegisterNewEmployerPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.navigateToRegisterNewEmployerPage();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://dev:dev@stage.pracawregionie.pl/user/auth/company_registration");
	}

	@Test(priority = 3)
	public void registerNewEmployer() {
		RegistrationEmplyerPage newEmployer = new RegistrationEmplyerPage(driver);
		newEmployer.registerNewEmplyer(fullEmail, password, strGender, userName, userSurname);

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

		GoogleEmailAccount accountUser = new GoogleEmailAccount(driver);
		accountUser.confirmRegistration();
	}

	@Test(priority = 5)
	public void loginToAccount() {
		driver.get(basicURL);
		Assert.assertEquals(driver.getTitle(), "Praca w Twoim regionie - pracawregionie.pl");
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/");

		MainPage mainPage = new MainPage(driver);
		mainPage.loginToAccount(fullEmail, password);

		Assert.assertTrue(driver.findElement(By.id("profile")).isDisplayed());
	}	

}
