package Utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtil {
	
	public static String captureScreenshot(String testName) {
		
		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") +
	              "/screenshots/" + testName + ".png";
		
		try {
			
			FileUtils.copyFile(src, new File(path));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return path;
	}

}
