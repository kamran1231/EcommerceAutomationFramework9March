package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;

public class AccountCreatedPage extends BasePage{
	
	
	By accountCreatedText = By.xpath("//b[normalize-space()='Account Created!']");
	By continueBtn = By.xpath("//a[normalize-space()='Continue']");
	
	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		
	}
	
	public String getAccountCreatedMessage() {
		return getText(accountCreatedText);
	}

	public String getAccountCreatedPageURL() {
		return driver.getCurrentUrl();
		
	}
	
	public void clickContinue() {
		click(continueBtn);
	}
}
