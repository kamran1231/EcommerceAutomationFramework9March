package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	static ExtentReports extent;
	
	public static ExtentReports getExtent() {
		
		if(extent == null) {
			
			String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";

			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
		}
		
		return extent;
		
	}

}
