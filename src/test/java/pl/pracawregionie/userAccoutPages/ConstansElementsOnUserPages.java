package pl.pracawregionie.userAccoutPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.pracawregionie.configs.PageObject;

public abstract class ConstansElementsOnUserPages extends PageObject{
	
	public ConstansElementsOnUserPages(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText = "Wyloguj mnie")
	WebElement loguotLink;
	
	@FindBy(linkText = "Dodaj CV")
	WebElement addCVLink;
	
	@FindBy(linkText = "Moje CV")
	WebElement myCVLink;
	
	@FindBy(linkText = "Moje kandydatury")
	WebElement myApplicationLink;
	
	@FindBy(linkText = "Edycja danych")
	WebElement credencialsEditLink;
	
	@FindBy(linkText = "Zmiana hasła")
	WebElement passwordChangeLink;
	
	@FindBy(xpath = "//*[@id='social-media']/a[1]/img")
	WebElement facebookImageButton;
	
	@FindBy(xpath = "//*[@id='social-media']/a[2]/img")
	WebElement twitterImageButton;
	
	@FindBy(xpath = "//*[@id='social-media']/a[3]/img")
	WebElement rssImageButton;
	
	@FindBy(linkText = "Strona główna")
	WebElement mainPageLink;
	
	@FindBy(linkText = "FAQ")
	WebElement faqLink;
	
	@FindBy(linkText = "Regulamin")
	WebElement regulaminLink;
	
	@FindBy(linkText = "Kontakt")
	WebElement contactLink;
	
	@FindBy(xpath = "//*[@id='page-wrapper']/footer/div[1]/div[1]/div/div/a")
	WebElement moreWorkLink;
		
	@FindBy(xpath = "//*[@id='page-wrapper']/footer/div[1]/div[2]/div/div/a")
	WebElement moreWork2Link;

}
