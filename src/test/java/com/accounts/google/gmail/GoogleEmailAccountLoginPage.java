package com.accounts.google.gmail;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pl.pracawregionie.tests.PageObject;

public class GoogleEmailAccountLoginPage extends PageObject {
	public GoogleEmailAccountLoginPage(WebDriver driver) {
		super(driver);
	}

	private String emailAddress = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
	private String emailAccountLogin = "testspwr@gmail.com";
	private String emailAccountPassword = "QWEqwe123";

	@FindBy(id = "identifierId")
	private WebElement logInField;

	@FindBy(xpath = "//*[@id='password']/div[1]/div/div[1]/input")
	private WebElement passField;

	@FindBy(xpath = "//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")
	private WebElement userMenuButton;

	@FindBy(id = "gb_71")
	private WebElement logOutButton;

	public void navigateToEmailAccountAndLogIn() {
		driver.get(emailAddress);
		logInField.sendKeys(emailAccountLogin);
		logInField.sendKeys(Keys.ENTER);
		passField.sendKeys(emailAccountPassword);
		passField.sendKeys(Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(emailAccountLogin + " - Gmail"));
	}

	public void logOutFromEmailAccount() {
		userMenuButton.click();
		logOutButton.click();
	}
}
