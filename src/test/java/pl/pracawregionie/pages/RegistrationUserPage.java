package pl.pracawregionie.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pl.pracawregionie.configs.PageObject;

public class RegistrationUserPage extends PageObject {

	public RegistrationUserPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "email")
	private WebElement emailField; // te same property sa dla Employera -> wyjebac do klasy nadrzędnej

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

	@FindBy(xpath = "//button[@type='submit'][@class='btn btn-success valid']")
	private WebElement confirmButton;

	public void registerNewUser(String fullEmail, String password, int gender) { // zamiast inta korzystaj z enuma - Gender
		emailField.clear();
		emailField.sendKeys(fullEmail);

		passwordField.clear();
		passwordField.sendKeys(password);

		passwordConfirmField.clear();
		passwordConfirmField.sendKeys(password);

		genderChecklist.selectByValue(Integer.toString(gender));
		
		Random randomCheckbox = new Random();
		
		for (int i = 0; i < 4; i++) {
			int indexCategoriesCheckboxes = randomCheckbox.nextInt(categoriesCheckboxes.size());		
			categoriesCheckboxes.get(indexCategoriesCheckboxes).click();
		}
		
		int indexVoivodeshipsCheckboxes = randomCheckbox.nextInt(voivodeshipsCheckboxes.size());
		voivodeshipsCheckboxes.get(indexVoivodeshipsCheckboxes).click();
		
		licenseAcceptanceCheckbox.click();
		termsOfUseCheckbox.click();
		confirmButton.submit();
	}

}
