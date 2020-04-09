package USGRegression;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import helper.utils.DriverSetter;
import helper.utils.captureScreenShot;

public class DesignStudio {
	public ExtentReports reports;
	WebDriver driver;
	ExtentTest logger;
	ExtentReports extent;
	
@BeforeMethod
	public void setup() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/newReport.html"));
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger=extent.createTest("LoginTest");
		logger=extent.createTest("EnterDetails");
	}
	
@Test(priority =1)
public void loginTest() throws IOException
{
	
	driver = DriverSetter.getChromeDriver();
	driver.get("http://www.google.com");
	System.out.println("title is "+driver.getTitle());
	Assert.assertTrue(driver.getTitle().contains("Mukesh"));
	
}

@Test(priority =2)
public void enterDetails() throws IOException
{
	driver = DriverSetter.getChromeDriver();
	driver.get("http://www.google.com");
	driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium Testing materials");
	driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).click();
	Assert.assertTrue(driver.getTitle().contains("pass"));
	
}

@AfterMethod
public void tearDown(ITestResult result) throws IOException
{
	
	if(result.getStatus()==ITestResult.FAILURE)
	{
		String temp=captureScreenShot.getScreenshot(driver);
		
		logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
	}
	
//	extent.flush();
	driver.quit();
	
}

@AfterTest
public void flushTest() {
	extent.flush();
}

}
