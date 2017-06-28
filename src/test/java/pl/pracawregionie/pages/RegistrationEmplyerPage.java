package pl.pracawregionie.pages;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pl.pracawregionie.configs.PageObject;

public class RegistrationEmplyerPage extends PageObject {

	public RegistrationEmplyerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "email")
	private WebElement emailField; // te same property sa dla Usera -> wyjebac do klasy nadrzędnej
	
	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "password_confirm")
	private WebElement passwordConfirmField;

	private Select genderChecklist = new Select(driver.findElement(By.name("courtesy_form")));

	@FindBy(id = "position")
	private WebElement positionField;

	@FindBy(id = "name")
	private WebElement nameField;

	@FindBy(id = "surname")
	private WebElement surnameField;

	@FindBy(id = "phone")
	private WebElement phoneField;

	private Select recruitmentTypeField = new Select(driver.findElement(By.name("recruitment_type_id")));

	@FindBy(id = "nip")
	private WebElement nipField;

	@FindBy(id = "regon")
	private WebElement regonField;

	@FindBy(id = "company_name")
	private WebElement companyNameField;

	@FindBy(id = "postal_code")
	private WebElement postalCodeField;

	@FindBy(id = "locality")
	private WebElement localityField;

	@FindBy(id = "address")
	private WebElement addressField;

	private Select voivodeshipField = new Select(driver.findElement(By.id("voivodeship_id")));

	private Select categoryField = new Select(driver.findElement(By.name("category_id")));

	@FindBy(id = "employee_count")
	private WebElement employeeCountField;

	@FindBy(id = "company_email")
	private WebElement companyEmailField;

	@FindBy(id = "company_phone")
	private WebElement companyPhoneField;

	@FindBy(id = "www")
	private WebElement wwwField;

	@FindBy(id = "description")
	private WebElement descriptionField;

	@FindBy(xpath = "//*[@id='registration']/div[2]/div/form/div[23]/div/span[1]/input")
	private WebElement registrationCheckbox;

	@FindBy(xpath = "//*[@id='registration']/div[2]/div/form/div[24]/div[1]/div/span[1]/input")
	private WebElement regulaminCheckbox;

	@FindBy(xpath = "//button[@type='submit'][@class='btn btn-success valid']")
	private WebElement confirmButton;

	public void registerNewEmplyer(String fullEmail, String password, String strGender, String userName, String userSurname) {
		emailField.clear();
		emailField.sendKeys(fullEmail);

		passwordField.clear();
		passwordField.sendKeys(password);

		passwordConfirmField.clear();
		passwordConfirmField.sendKeys(password);

		genderChecklist.selectByValue(strGender);

		positionField.clear();
		positionField.sendKeys(RandomStringUtils.randomAlphabetic(10));

		nameField.clear();
		nameField.sendKeys(userName);
		
		surnameField.clear();
		surnameField.sendKeys(userSurname);

		phoneField.clear();
		phoneField.sendKeys(RandomStringUtils.randomNumeric(9));

		recruitmentTypeField.selectByValue(Integer.toString(new Random().nextInt(3) + 1));

		nipField.clear();
		nipField.sendKeys("9197294419"); //do stałej final z tym

		regonField.clear();
		regonField.sendKeys("697425515");  //do stałej final z tym

		companyNameField.clear();
		companyNameField.sendKeys("TESTTEST " + RandomStringUtils.randomAlphabetic(10));  //do stałej final z tym

		postalCodeField.clear();
		positionField.sendKeys(RandomStringUtils.randomAlphabetic(2) + "-" + RandomStringUtils.randomAlphabetic(3));

		localityField.clear();
		localityField.sendKeys(RandomStringUtils.randomAlphabetic(10));

		addressField.clear();
		addressField.sendKeys(RandomStringUtils.randomAlphabetic(10));

		Random random = new Random();
		voivodeshipField.selectByValue(Integer.toString(random.nextInt(19) + 1));

		//Niewiem o co tu chodzi, jak cos to podpytaj
		categoryField.selectByValue("32"); // ??????????????? pomyslec jak to rzowiazac, rozne numery sa

		employeeCountField.clear();
		employeeCountField.sendKeys(RandomStringUtils.randomNumeric(2));

		companyEmailField.clear();
		companyEmailField.sendKeys(fullEmail);

		phoneField.clear();
		phoneField.sendKeys(RandomStringUtils.randomNumeric(9));

		wwwField.clear();
		wwwField.sendKeys("www.google.pl");  //do stałej final z tym

		descriptionField.clear();
		descriptionField.sendKeys(RandomStringUtils.randomAlphabetic(8) + " " + RandomStringUtils.randomAlphabetic(8)
				+ " " + RandomStringUtils.randomAlphabetic(8));

		registrationCheckbox.click();

		regulaminCheckbox.click();

		confirmButton.submit();
	}

}
