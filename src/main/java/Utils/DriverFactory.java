package Utils;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static WebDriver initDriver(String browser) {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();

		// Disable password popup
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.autofill_profile_enabled", false);
		prefs.put("autofill.profile_enabled", false);

		// Disable notifications
		prefs.put("profile.default_content_setting_values.notifications", 2);

		options.setExperimentalOption("prefs", prefs);

		// Additional blocking args
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-notifications");

		options.addArguments("--disable-infobars");

		options.addArguments("--disable-extensions");

		options.addArguments("--disable-popup-blocking");

		options.addArguments("--disable-geolocation");

		try {

			if (browser.equalsIgnoreCase("chrome")) {

				String runMode = System.getProperty("runMode");

				// LOCAL Execution
				if (runMode == null || runMode.equalsIgnoreCase("local")) {

					WebDriverManager.chromedriver().setup();
					tlDriver.set(new ChromeDriver(options));

				}

				// DOCKER Execution
				else if (runMode.equalsIgnoreCase("docker")) {

					tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return tlDriver.get();

	}

	public static void quitDriver() {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}

}
