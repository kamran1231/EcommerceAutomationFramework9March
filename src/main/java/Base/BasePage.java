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
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Robust click method

	public void click(By locator) {

		try {

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();

		} catch (Exception e) {

			WebElement element = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);

			js.executeScript("arguments[0].click();", element);
		}
	}
	
	//Robust type method
	public void type(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		
		element.sendKeys(text);
	}
	
	//Get text
	public String getText(By locator) {
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

}
