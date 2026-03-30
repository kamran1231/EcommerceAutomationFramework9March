package Pages;

import java.util.List;

import org.openqa.selenium.By;
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

		return By.xpath("//div[@id='" + category + "']//a[contains(text(),'" + subCategory + "')]");
	}
	
	// =============================
	// CATEGORY PAGE HEADER
	// =============================

	public By categoryHeader(String category, String subCategory) {

	    return By.xpath(
	        "//h2[normalize-space()='"
	        + category
	        + " - "
	        + subCategory
	        + " Products']"
	    );

	}
	
	// =============================
	// VERIFY CATEGORY PAGE TITLE
	// =============================
	
	public boolean isCategoryPageDisplayed(String category, String subCategory) {
				
		return isElementDisplayed(categoryHeader(category, subCategory));
	}
	
	public String getCategoryHeaderText(
	        String category,
	        String subCategory) {

	    return getText(
	            categoryHeader(
	                    category,
	                    subCategory));

	}

	// =============================
	// PRODUCT LIST
	// =============================



	By productList = By.xpath("//div[@class='productinfo text-center']");

	// =============================
	// SELECT CATEGORY
	// =============================

	public void selectCategory(String category, String subCategoryName) {

		click(categoryExpand(category));

		click(subCategory(category, subCategoryName));
	}

	// =============================
	// VERIFY PRODUCTS DISPLAYED
	// =============================

	public int getProductCount() {

		List<WebElement> products = driver.findElements(productList);

		return products.size();
	}

}
