package Tests;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import Utils.ConfigReader;
import Utils.DriverFactory;

public class BaseTest {

	protected WebDriver driver;

	//@Parameters("browser")
	@BeforeMethod
	public void setup() {
		
		String browser =  ConfigReader.getProperty("browser");
		DriverFactory.initDriver(browser);
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.getProperty("baseUrl"));

	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
