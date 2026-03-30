package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void handleVignetteAd() {

		try {

			for (int i = 0; i < 5; i++) {

				if (driver.getCurrentUrl().contains("google_vignette")) {

					System.out.println("Vignette Ad Detected — Closing");

					driver.navigate().back();

					Thread.sleep(1000);

				} else {

					break;

				}

			}

		} catch (Exception e) {

			System.out.println("No Vignette Ad Found");

		}

	}

	// Robust click method

	public void click(By locator) {

	    handleVignetteAd();

	    WebElement element =
	            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	    try {

	        // Scroll to element center
	        ((JavascriptExecutor) driver)
	                .executeScript(
	                        "arguments[0].scrollIntoView({block:'center'});",
	                        element);

	        // Small pause for overlay stability
	        Thread.sleep(500);

	        // Wait clickable using element (not locator)
	        wait.until(ExpectedConditions.elementToBeClickable(element));

	        element.click();

	    } catch (Exception e) {

	        System.out.println("Normal click failed — using JS click");

	        ((JavascriptExecutor) driver)
	                .executeScript(
	                        "arguments[0].click();",
	                        element);
	    }

	    handleVignetteAd();

	}

	// Robust remove ad
	public void removeBottomAdIfPresent() {
		try {
			((JavascriptExecutor) driver).executeScript("var ads = document.querySelectorAll('iframe');"
					+ "ads.forEach(a => { if (a.src && a.src.includes('google')) a.remove(); });");
		} catch (Exception ignored) {
		}
	}

	// Robust type method
	public void type(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();

		element.sendKeys(text);
	}

	// Get text
	public String getText(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public void selectRadioButton(By locator, String value) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		for (WebElement radio : driver.findElements(locator)) {

			if (radio.getAttribute("value").equalsIgnoreCase(value)) {

				if (!radio.isSelected()) {

					try {
						radio.click();
					} catch (Exception e) {

						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", radio);
						js.executeScript("arguments[0].click();", radio);
					}
				}

				break;
			}
		}

	}

	// Robust Element displayed
	public boolean isElementDisplayed(By locator) {

		handleVignetteAd();
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			return driver.findElement(locator).isDisplayed();

		} catch (Exception e) {

			return false;

		}

	}

	// SCROLL

	public void scrollToElement(By locator) {

	    WebElement element =
	            waitForElementVisible(locator);

	    JavascriptExecutor js =
	            (JavascriptExecutor) driver;

	    js.executeScript(
	        "arguments[0].scrollIntoView({block:'center'});",
	        element
	    );

	}

	public WebElement waitForElementVisible(By locator) {

		handleVignetteAd();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

}
