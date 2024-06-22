package automation.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	public static ExtentReports getReportObject() {
		File file = new File(System.getProperty("user.dir") + "/reports/" + "index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", "Kumar Satyajit");

		return extentReports;
	}
}
