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

	// Robust click method

	public void click(By locator) {
	    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	    try {
	        // Scroll element to center so nothing covers it
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	    } catch (Exception e) {
	        // JS click fallback (bypasses overlays)
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", element);
	    }
	}
	
	// Robust remove ad
	public void removeBottomAdIfPresent() {
	    try {
	        ((JavascriptExecutor) driver).executeScript(
	            "var ads = document.querySelectorAll('iframe');" +
	            "ads.forEach(a => { if (a.src && a.src.includes('google')) a.remove(); });"
	        );
	    } catch (Exception ignored) {}
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

}
