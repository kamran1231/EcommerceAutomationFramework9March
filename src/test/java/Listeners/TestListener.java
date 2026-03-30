package Listeners;

import org.apache.logging.log4j.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentManager;
import Utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	private static final Logger logger = LogManager.getLogger(TestListener.class);

	ExtentReports extent = ExtentManager.getExtent();
	ExtentTest test;

	public void onTestStart(ITestResult result) {

		logger.info("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {

		logger.info("Test Passed: " + result.getName());
		test.pass("Test Passed");

	}

	public void onTestFailure(ITestResult result) {

		logger.error("Test Failed: " + result.getName());
		logger.error("Reason: " + result.getThrowable());
		test.fail(result.getThrowable());

		String screenshotpath =
				ScreenshotUtil.captureScreenshot(result.getName());

				if (screenshotpath != null) {

				    try {

				        test.addScreenCaptureFromPath(screenshotpath);

				    }

				    catch (Exception e) {

				        e.printStackTrace();

				    }

				}

	}

	public void onTestSkipped(ITestResult result) {

		logger.warn("Test Skipped: " + result.getName());
		test.skip("Test Skipped");
	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
