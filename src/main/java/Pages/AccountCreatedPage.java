package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BasePage;

public class AccountCreatedPage extends BasePage{
	
	//b[normalize-space()='Account Created!']
	
	By accountCreatedText = By.xpath("//b[normalize-space()='Account Created!']");
	By continueBtn = By.xpath("//a[@data-qa='continue-button']");
	
	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		
	}
	
	public String getAccountCreatedMessage() {
		 //wait.until(ExpectedConditions.urlContains("account_created"));

	        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));

	        return getText(accountCreatedText);
	
	}

	public String getAccountCreatedPageURL() {
		return driver.getCurrentUrl();
		
	}
	
	public void clickContinue() {
		click(continueBtn);
	}
}
