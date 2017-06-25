package pl.pracawregionie.tests;

import org.testng.annotations.Test;

import pl.pracawregionie.pages.MainPage;
import pl.pracawregionie.pages.RegistrationEmplyerPage;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Test2RegisterEmployer extends ConfigForTests{

	public Test2RegisterEmployer() throws Exception {
		super();
	}

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
	public void navigationToRegisterNewEmployerPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.navigateToRegisterNewEmployerPage();
		Assert.assertEquals(driver.getCurrentUrl(), "http://dev:dev@stage.pracawregionie.pl/user/auth/company_registration");
	}
	
	@Test(priority = 3)
	public void registerNewEmployer() {
		RegistrationEmplyerPage newEmployer = new RegistrationEmplyerPage(driver);
		newEmployer.registerNewEmplyer(FULL_EMAIL, PASSWORD, gender2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success template']")).isDisplayed());
		Assert.assertTrue(driver.getPageSource().contains(
				"Zarejestrowano pomyślnie. Na podany adres email została wysłana wiadomość z linkiem aktywacyjnym."));		
		Assert.assertEquals(driver.getCurrentUrl(), "http://stage.pracawregionie.pl/user/auth/login");
	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
