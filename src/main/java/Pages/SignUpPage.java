package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Base.BasePage;

public class SignUpPage extends BasePage {


	By title = By.xpath("//input[@id='id_gender1']");
	By password = By.xpath("//input[@id='password']");
	By dayDropdown = By.xpath("//select[@id='days']");
	By monthDropdown = By.xpath("//select[@id='months']");
	By yearDropdown = By.xpath("//select[@id='years']");
	By newsLetterCheckBox = By.xpath("//input[@id='newsletter']");
	By specialOfferCheckBox = By.xpath("//input[@id='optin']");
	By firstName = By.xpath("//input[@id='first_name']");
	By lastName = By.xpath("//input[@id='last_name']");
	By company = By.xpath("//input[@id='company']");
	By address = By.xpath("//input[@id='address1']");
	By countryDropdown = By.xpath("//select[@id='country']");
	By state = By.xpath("//input[@id='state']");
	By city = By.xpath("//input[@id='city']");
	By zipcode = By.xpath("//input[@id='zipcode']");
	By mobileNumber = By.xpath("//input[@id='mobile_number']");
	By createAccountBTN = By.xpath("//button[normalize-space()='Create Account']");

	public SignUpPage(WebDriver driver) {

		super(driver);
	}

	public SignUpPage selectTitle() {
		click(title);
		return this;
	}

	public SignUpPage inputPassword(String psw) {
		type(password, psw);
		return this;
	}

	public SignUpPage selectByValue(By locator, String value) {
		new Select(driver.findElement(locator)).selectByValue(value);
		return this;
	}

	public SignUpPage selectDOB(String day, String month, String year) {

		selectByValue(dayDropdown, day);
		selectByValue(monthDropdown, month);
		selectByValue(yearDropdown, year);

		return this;

	}

	public SignUpPage checkNewsLetterAndSpecialOfferBox() {
		click(newsLetterCheckBox);
		click(specialOfferCheckBox);
		

		return this;

	}

	public SignUpPage inputFirstName(String fname) {
		type(firstName, fname);
		return this;
	}

	public SignUpPage inputLastName(String lname) {
		type(lastName, lname);
		return this;

	}

	public SignUpPage inputCompanyName(String companyName) {
		type(company, companyName);
		return this;
	}

	public SignUpPage inputAddress(String addressName) {
		type(address, addressName);
		return this;
	}

	public SignUpPage selectCountry(String countryName) {
		selectByValue(countryDropdown, countryName);
		return this;

	}

	public SignUpPage inputState(String stateName) {
		type(state, stateName);
		return this;
	}

	public SignUpPage inputCity(String cityName) {
		type(city, cityName);
		return this;
	}

	public SignUpPage inputZipcode(String zip) {
		type(zipcode, zip);
		return this;
	}

	public SignUpPage inputMobileNumber(String mobile) {
		type(mobileNumber, mobile);
		return this;

	}

	public AccountCreatedPage clickOnCreateAccountBtn() {
		click(createAccountBTN);
		return new AccountCreatedPage(driver);

	}

}
