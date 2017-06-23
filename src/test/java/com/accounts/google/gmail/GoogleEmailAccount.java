package com.accounts.google.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.pracawregionie.tests.PageObject;

public class GoogleEmailAccount extends PageObject{
	public GoogleEmailAccount(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "")
	private WebElement a;

}
