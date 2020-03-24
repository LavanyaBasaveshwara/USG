package USGRegression;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DesignStudio {
	public ExtentReports reports;

	public void setup() {
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/newReport.html"));
	}
	
	
}
