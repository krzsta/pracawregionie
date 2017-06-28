package pl.pracawregionie.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pl.pracawregionie.userAccoutPages.ConstansElementsOnUserPages;

public class MainPage extends ConstansElementsOnUserPages {

	public MainPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(partialLinkText = "Logowanie")
	WebElement loginLink;

	@FindBy(id = "email")
	WebElement emailField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(id = "dropdownMenu")
	private WebElement registerMenuButton;

	@FindBy(linkText = "Rejestracja pracownika")
	private WebElement registerUserLink;

	@FindBy(linkText = "Rejestracja pracodawcy")
	private WebElement registerEmployerLink;
	
	@FindBy(xpath = "/html/body/div[2]/div/div")
	WebElement loginAlertWindow;

	public void navigateToRegisterNewUserPage() {
		registerMenuButton.click();
		registerUserLink.click();
	}

	public void navigateToRegisterNewEmployerPage() {
		registerMenuButton.click();
		registerEmployerLink.click();
	}

	public void loginToAccount(String fullEmail, String password) {
		loginLink.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(loginAlertWindow));

		emailField.clear();
		emailField.sendKeys(fullEmail);

		passwordField.clear();
		passwordField.sendKeys(password);
		passwordField.sendKeys(Keys.ENTER);
	}

}
