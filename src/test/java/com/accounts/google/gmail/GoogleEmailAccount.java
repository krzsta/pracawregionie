package com.accounts.google.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.pracawregionie.tests.PageObject;

public class GoogleEmailAccount extends PageObject{
	public GoogleEmailAccount(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "pracawregionie.pl")
	private WebElement confirmEmail;
	
	@FindBy(partialLinkText = "http://stage.pracawregionie.pl/user/auth/activate/")
	WebElement confirmLink;
	
	public void confirmRegistration() {
		confirmEmail.click();
		confirmLink.click();
	}
	
}
