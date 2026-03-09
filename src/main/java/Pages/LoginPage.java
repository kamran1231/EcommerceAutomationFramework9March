package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;

public class LoginPage extends BasePage {


	By name = By.xpath("//input[@placeholder='Name']");
	By email = By.xpath("//input[@data-qa='signup-email']");
	By signUpBtn = By.xpath("//button[normalize-space()='Signup']");

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

}
