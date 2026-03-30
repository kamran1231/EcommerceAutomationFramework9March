package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;

public class HomePageTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;

	@Test(dataProvider = "validloginData", dataProviderClass = LoginTest.class)
	public void verifyCategoryAfterLogin(String email, String password, String category, String subCategory,
			String brand) {

		loginPage = new LoginPage(driver);

		// =========================
		// LOGIN
		// =========================

		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickOnLoginBtn();

		String actualText = loginPage.getLoggedInUserText();

		Assert.assertTrue(actualText.contains("Full-Fledged practice website for Automation Engineers"));

		// =========================
		// CATEGORY SELECTION
		// =========================

		homePage = new HomePage(driver);

		homePage.selectCategory(category, subCategory);

		// =========================
		// VALIDATION
		// =========================

		Assert.assertTrue(

				homePage.isCategoryPageDisplayed(category, subCategory),

				category.toUpperCase() + subCategory.toUpperCase() + "products page not displayed"

		);

		String actualHeader = homePage.getCategoryHeaderText(category, subCategory);

		Assert.assertEquals(

				actualHeader,

				category.toUpperCase() + " - " + subCategory.toUpperCase() + " PRODUCTS"

		);

	}

	@Test(dataProvider = "validloginData", dataProviderClass = LoginTest.class)
	public void verifyBrandSelection(String email, String password, String category, String subCategory, String brand) {

		loginPage = new LoginPage(driver);

		// LOGIN

		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickOnLoginBtn();

		String actualText = loginPage.getLoggedInUserText();

		Assert.assertTrue(actualText.contains("Full-Fledged practice website for Automation Engineers"));

		homePage = new HomePage(driver);

		// SELECT BRAND

		homePage.selectBrand(brand);

		// VALIDATION

		Assert.assertTrue(homePage.isBrandPageDisplayed(brand), "Brand page not displayed");

		String actualHeader = homePage.getBrandHeaderText(brand);

		String expectedHeader = "BRAND - " + brand.toUpperCase() + " PRODUCTS";

		Assert.assertEquals(actualHeader, expectedHeader);

	}

}
