package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;

public class LoginPage extends BasePage {

	By name = By.xpath("//input[@placeholder='Name']");
	By email = By.xpath("//input[@data-qa='signup-email']");
	By signUpBtn = By.xpath("//button[normalize-space()='Signup']");
	By emailAlreadyAlertMsg = By.xpath("//p[contains(text(),'Email Address already exist!')]");
	By emailAdd = By.xpath("//input[contains(@data-qa,'login-email')]");
	By password = By.xpath("//input[contains(@data-qa,'login-password')]");
	By loginBtn = By.xpath("//button[contains(@data-qa,'login-button')]");
	By loggedInUserText = By.xpath(
			"//div[contains(@class,'item') and contains(@class,'active')]//h2[contains(text(),'Full-Fledged practice website')]");
	By loginErrorMsg = By.xpath("//p[contains(normalize-space(),'Your email or password is incorrect')]");

	public LoginPage(WebDriver driver) {

		super(driver);
	}

	public LoginPage enterName(String username) {
		type(name, username);
		return this;
	}

	public LoginPage enterEmail(String useremail) {
		type(email, useremail);
		return this;
	}

	public SignUpPage clickSignup() {
		click(signUpBtn);
		return new SignUpPage(driver);
	}

	public String getEmailAlertAlreadyMSG() {
		return getText(emailAlreadyAlertMsg);

	}

	public LoginPage inputEmail(String userEmail) {
		type(emailAdd, userEmail);
		return this;
	}

	public LoginPage inputPassword(String userPsw) {
		type(password, userPsw);
		return this;

	}

	public HomePage clickOnLoginBtn() {
		click(loginBtn);
		return new HomePage(driver);
	}

	public String getLoggedInUserText() {
		return getText(loggedInUserText);
	}
	
	public String getLoginErrorMessage() {
		return getText(loginErrorMsg);
	}
}
