package pl.pracawregionie.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pl.pracawregionie.tests.PageObject;

public class RegistrationUserPage extends PageObject {

	public RegistrationUserPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "password_confirm")
	private WebElement passwordConfirmField;

	private Select genderChecklist = new Select(driver.findElement(By.name("gender")));

	private List<WebElement> categoriesCheckboxes = driver.findElements(By.name("categories[]"));

	private List<WebElement> voivodeshipsCheckboxes = driver.findElements(By.name("voivodeships[]"));

	@FindBy(name = "license_acceptance")
	private WebElement licenseAcceptanceCheckbox;

	@FindBy(name = "terms_of_use")
	private WebElement termsOfUseCheckbox;

	@FindBy(className = "btn btn-success valid")
	private WebElement confirmButton;

	public void registerNewUser(String FULL_EMAIL, String PASSWORD) {
		emailField.clear();
		emailField.sendKeys(FULL_EMAIL);

		passwordField.clear();
		passwordField.sendKeys(PASSWORD);

		passwordConfirmField.clear();
		passwordConfirmField.sendKeys(PASSWORD);

		genderChecklist.selectByValue("0");

		for (WebElement el : categoriesCheckboxes) {
			el.click();
		}

		for (WebElement el : voivodeshipsCheckboxes) {
			el.click();
		}

		licenseAcceptanceCheckbox.click();
		termsOfUseCheckbox.click();
	}

	public boolean categoriesCheckboxesAssert() {
		for (WebElement el : categoriesCheckboxes) {
			el.isSelected();
		}
		return true;
	}
	
	public boolean voivodeshipsCheckboxesAssert() {
		for (WebElement el : voivodeshipsCheckboxes) {
			el.isSelected();
		}
		return true;
	}

}
