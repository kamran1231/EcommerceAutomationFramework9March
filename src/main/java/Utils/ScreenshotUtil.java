package Utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String captureScreenshot(String testName) {
		
		String path = null;

        try {

            WebDriver driver =
                    DriverFactory.getDriver();

            // 🔴 IMPORTANT NULL CHECK

            if (driver == null) {

                System.out.println(
                    "Driver is NULL — Screenshot skipped"
                );

                return null;
            }

            File src =
                    ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            path =
                    System.getProperty("user.dir")
                    + "/screenshots/"
                    + testName
                    + ".png";

            File dest =
                    new File(path);

            FileUtils.copyFile(src, dest);

        }

        catch (Exception e) {

            System.out.println(
                "Screenshot failed: "
                + e.getMessage()
            );

        }

        return path;
    }

}
