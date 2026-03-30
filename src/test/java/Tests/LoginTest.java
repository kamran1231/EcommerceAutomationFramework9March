package Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utils.ExcelUtil;

public class LoginTest extends BaseTest {

	LoginPage loginPage;

	@DataProvider(name = "validloginData")
	public Object[][] getValidLoginData() {
		return ExcelUtil.getTestData("ValidLoginData");
	}
	
	@DataProvider(name = "invalidloginData")
	public Object[][] getInvalidLoginData() {
		return ExcelUtil.getTestData("InvalidLoginData");
	}

	@Test(dataProvider = "validloginData")
	public void loginWithValidCredential(String email, String password, String category, String subCategory) {

		loginPage = new LoginPage(driver);

		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickOnLoginBtn();
		
		

		String actualText = loginPage.getLoggedInUserText();

		Assert.assertTrue(actualText.contains("Full-Fledged practice website for Automation Engineers"));
	}

	@Test(dataProvider = "invalidloginData")
	public void loginWithInvalidPassword(String email, String password) {

		loginPage = new LoginPage(driver);

		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickOnLoginBtn();

		String actualText = loginPage.getLoginErrorMessage();

		Assert.assertEquals(actualText, "Your email or password is incorrect!");

	}

}
