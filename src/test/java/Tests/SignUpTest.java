package Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.AccountCreatedPage;
import Pages.LoginPage;
import Pages.SignUpPage;
import Utils.ExcelUtil;

public class SignUpTest extends BaseTest {

	LoginPage loginPage;

	@DataProvider(name = "signupData")
	public Object[][] getSignupData() {

		return ExcelUtil.getTestData("SignupData");

	}
	
	
	@Test(dataProvider = "signupData")
	public void validSignupTest(String title,
	        String name,
	        String password,
	        String day,
	        String month,
	        String year,
	        String firstname,
	        String lastname,
	        String company,
	        String address,
	        String address2,
	        String country,
	        String state,
	        String city,
	        String zipcode,
	        String mobile) {

		// Login page actions
		loginPage = new LoginPage(driver);
		
		// Generate dynamic email
		String email = name + System.currentTimeMillis() + "@mailinator.com";

		// Generate dynamic email
		SignUpPage signup = loginPage.enterName(name).enterEmail(email).clickSignup();

		// SignUp page Actions
		// Step 2 — Remaining fields
		AccountCreatedPage accountPage = signup.selectTitle(title).inputPassword(password).selectDOB(day, month, year)
				.checkNewsLetterAndSpecialOfferBox().inputFirstName(firstname).inputLastName(lastname)
				.inputCompanyName(company).inputAddress(address).selectCountry(country).inputState(state)
				.inputCity(city).inputZipcode(zipcode).inputMobileNumber(mobile).clickOnCreateAccountBtn();

		// Validation
		String actualMessage = accountPage.getAccountCreatedMessage();
		String accountCreatedURL = accountPage.getAccountCreatedPageURL();
		Assert.assertEquals(actualMessage, "ACCOUNT CREATED!");

		Assert.assertEquals(accountCreatedURL, "https://automationexercise.com/account_created");

//	@Test
//	public void validSignupTest() {
//
//		// Login page actions
//		loginPage = new LoginPage(driver);
//		String email = "kamran" + System.currentTimeMillis() + "@mailinator.com";
//
//		SignUpPage signup = loginPage.enterName("kamran").enterEmail(email).clickSignup();
//
//		// SignUp page Actions
//		AccountCreatedPage accountPage = signup.selectTitle("Mr").inputPassword("Test@123").selectDOB("10", "5", "1998")
//				.checkNewsLetterAndSpecialOfferBox().inputFirstName("Kamran").inputLastName("Khan")
//				.inputCompanyName("ABC Company").inputAddress("Delhi India").selectCountry("India").inputState("Delhi")
//				.inputCity("Delhi").inputZipcode("110001").inputMobileNumber("9999999999").clickOnCreateAccountBtn();
//
//		// Validation
//		String actualMessage = accountPage.getAccountCreatedMessage();
//		String accountCreatedURL = accountPage.getAccountCreatedPageURL();
//		Assert.assertEquals(actualMessage, "ACCOUNT CREATED!");
//
//		Assert.assertEquals(accountCreatedURL, "https://automationexercise.com/account_created");

	}

	@Test
	public void signupWithExistingEmailTest() {
		loginPage = new LoginPage(driver);
		loginPage.enterName("kamran").enterEmail("kamran44@mailinator.com").clickSignup();

		String actualError = loginPage.getEmailAlertAlreadyMSG();

		Assert.assertEquals(actualError, "Email Address already exist!");
	}
}
