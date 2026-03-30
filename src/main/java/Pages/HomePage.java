package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		handleVignetteAd();
	}

	// =============================
	// CATEGORY EXPAND
	// =============================

	public By categoryExpand(String category) {

		return By.xpath("//a[@href='#" + category + "']");
	}

	// =============================
	// SUB CATEGORY
	// =============================

	public By subCategory(String category, String subCategory) {

		return By.xpath(
			    "//div[@id='" + category + "']"
			    + "//a[normalize-space()='"
			    + subCategory + "']"
			);
	}

	// =============================
	// CATEGORY PAGE HEADER
	// =============================

	public By categoryHeader(String category, String subCategory) {

		return By.xpath("//h2[normalize-space()='" + category + " - " + subCategory + " Products']");

	}

	// =============================
	// VERIFY CATEGORY PAGE TITLE
	// =============================

	public boolean isCategoryPageDisplayed(String category, String subCategory) {

		return isElementDisplayed(categoryHeader(category, subCategory));
	}

	public String getCategoryHeaderText(String category, String subCategory) {

		return getText(categoryHeader(category, subCategory));

	}

	// =============================
	// PRODUCT LIST
	// =============================

	By productList = By.xpath("//div[@class='productinfo text-center']");

	// =============================
	// SELECT CATEGORY
	// =============================

	public void selectCategory(String category, String subCategoryName) {

	    handleVignetteAd();

	    JavascriptExecutor js =
	            (JavascriptExecutor) driver;

	    // Expand category (Women/Men/Kids)

	    By categoryLocator =
	            By.xpath("//a[@href='#" + category + "']");

	    WebElement categoryElement =
	            waitForElementVisible(categoryLocator);

	    js.executeScript(
	            "arguments[0].scrollIntoView({block:'center'});",
	            categoryElement
	    );

	    js.executeScript(
	            "arguments[0].click();",
	            categoryElement
	    );

	    // Wait subcategory visible

	    By subCategoryLocator =
	            By.xpath(
	                    "//div[@id='" + category + "']"
	                    + "//a[normalize-space()='"
	                    + subCategoryName + "']"
	            );

	    WebElement subCategoryElement =
	            waitForElementVisible(subCategoryLocator);

	    js.executeScript(
	            "arguments[0].click();",
	            subCategoryElement
	    );

	}

	// =============================
	// VERIFY PRODUCTS DISPLAYED
	// =============================

	public int getProductCount() {

		List<WebElement> products = driver.findElements(productList);

		return products.size();
	}

	// =============================
	// BRAND LOCATOR
	// =============================

	public By brandLocator(String brand) {

		return By.xpath("//a[@href='/brand_products/"+brand+"']");

	}

	// =============================
	// SELECT BRAND
	// =============================

	public void selectBrand(String brand) {

	    handleVignetteAd();

	    JavascriptExecutor js =
	            (JavascriptExecutor) driver;

	    // Scroll to brand section
	    By brandSection =
	            By.xpath("//div[@class='brands-name']");

	    WebElement section =
	            waitForElementVisible(brandSection);

	    js.executeScript(
	        "arguments[0].scrollIntoView({block:'center'});",
	        section
	    );

	    // Click brand
	    WebElement brandElement =
	            waitForElementVisible(
	                    brandLocator(brand));

	    js.executeScript(
	        "arguments[0].click();",
	        brandElement
	    );

	    handleVignetteAd();

	}

	// =============================
	// BRAND HEADER
	// =============================

	public By brandHeader(String brand) {

	    return By.xpath(
	        "//h2[normalize-space()='Brand - "
	        + brand +
	        " Products']"
	    );

	}

	public String getBrandHeaderText(String brand) {

		return getText(By.xpath("//h2[contains(text(),'" + brand + "')]"));

	}

	public boolean isBrandPageDisplayed(String brand) {

		waitForElementVisible(brandHeader(brand));

		return isElementDisplayed(brandHeader(brand));

	}

}
