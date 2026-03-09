package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AccountCreatedPage;
import Pages.LoginPage;
import Pages.SignUpPage;

public class SignUpTest extends BaseTest {

	@Test
	public void validSignupTest() {

		// Login page actions
		LoginPage loginPage = new LoginPage(driver);

		SignUpPage signup = loginPage.enterName("kamran").enterEmail("kamran43@mailinator.com").clickSignup();

		// SignUp page Actions
		AccountCreatedPage accountPage = signup.selectTitle().inputPassword("Test@123").selectDOB("10", "5", "1998")
				.checkNewsLetterAndSpecialOfferBox().inputFirstName("Kamran").inputLastName("Khan")
				.inputCompanyName("ABC Company").inputAddress("Delhi India").selectCountry("India").inputState("Delhi")
				.inputCity("Delhi").inputZipcode("110001").inputMobileNumber("9999999999").clickOnCreateAccountBtn();

		// Validation
		String actualMessage = accountPage.getAccountCreatedMessage();
		String accountCreatedURL = accountPage.getAccountCreatedPageURL();
		Assert.assertEquals(actualMessage, "ACCOUNT CREATED!");
		
		Assert.assertEquals(accountCreatedURL, "https://automationexercise.com/account_created");

	}
}
