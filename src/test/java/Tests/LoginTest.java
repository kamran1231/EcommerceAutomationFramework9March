package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utils.ConfigReader;

public class LoginTest extends BaseTest{
	
	LoginPage loginPage;
	
	@Test
	public void loginWithValidCredential() {
		
		loginPage = new LoginPage(driver);
		
		loginPage.inputEmail(ConfigReader.getProperty("validEmail"));
		loginPage.inputPassword(ConfigReader.getProperty("ValidPsw"));
		loginPage.clickOnLoginBtn();
		
		String actualText = loginPage.getLoggedInUserText();
		
		
		Assert.assertTrue(actualText.contains("Full-Fledged practice website for Automation Engineers"));
	}
	
	@Test
	public void loginWithInvalidPassword() {
		
loginPage = new LoginPage(driver);
		
		loginPage.inputEmail(ConfigReader.getProperty("validEmail"));
		loginPage.inputPassword(ConfigReader.getProperty("WrongPsw"));
		loginPage.clickOnLoginBtn();
		
		String actualText = loginPage.getLoginErrorMessage();
		
		Assert.assertEquals(actualText, "Your email or password is incorrect!");
		
	}
	

}
