package pl.pracawregionie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.pracawregionie.tests.PageObject;

public class MainPage extends PageObject {
	
	public MainPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id = "dropdownMenu")
	private WebElement registerMenuButton;
	
	@FindBy(linkText = "Rejestracja pracownika")
	private WebElement registerUserLink;
	
	public void clickOnRegMenuButton() {	
		registerMenuButton.click();
	}
	
	public void clickOnRegUserLink(){
		registerUserLink.click();
	}
	
	public void navigateToRegisterNewUserPage(){
		clickOnRegMenuButton();
		clickOnRegUserLink();
	}

}
