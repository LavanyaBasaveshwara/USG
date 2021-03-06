package USGRegression;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import USGRegressionObjects.BuildSubmittalObject;
import USGRegressionObjects.HomePageObjects;
import Utile.CaptureScreenshot;
import helper.utils.DriverSetter;

import org.testng.annotations.BeforeMethod;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class LoginCsvParameterTest {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		driver = DriverSetter.getChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get("https://www.usg.com/content/usgcom/en.html");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			CaptureScreenshot.takeSnapShot(driver, result.getName());
			driver.quit();
		}
		driver.quit();
	}


//	@Test(priority = 3, description = "Test to validate the Build a Submittal Page is displayed")

	@Parameters({ "email", "password" })
	public void BuildSubPageDisplay(String email, String password) throws InterruptedException {
		HomePageObjects.waitForBuildSub_Btn(wait);
		HomePageObjects.buildSubBtn(driver).click();
		BuildSubmittalObject.SubmittalDisplay(driver);
		Thread.sleep(3000);
		BuildSubmittalObject.PassLoginID(driver).sendKeys(email);
		BuildSubmittalObject.PassPassword(driver).sendKeys(password);
		BuildSubmittalObject.LoginClick(driver).click();
		BuildSubmittalObject.waitForPageDisplay(wait); // y am i getting this even though the method is declared
		Thread.sleep(5000);
		BuildSubmittalObject.LoginValidation(driver);

	}

	@DataProvider(name = "loginParams")
	public Object[][] dp() throws IOException {
		Object[][] params = new Object[][] {};
		CSVReader csvReader = new CSVReader(new FileReader("./src/test/resources/loginParams.csv"), ',');
		
		List<String[]> allLines = csvReader.readAll();
		if(!allLines.isEmpty()) {
			params = new Object[allLines.size()][2];
			for(int i=0;i<allLines.size();i++) {
				String[] lineValues = allLines.get(i);
				params[i][0] = lineValues[0];
				params[i][1] = lineValues[1];
			}
		}
		
		return params;

	}

	@Test(priority = 3, description = "Test to validate the Build a Submittal Page is displayed",dataProvider = "loginParams")

	public void BuildSubPageDisplayWithDataProvider(String email, String password) throws InterruptedException {
		HomePageObjects.waitForBuildSub_Btn(wait);
		HomePageObjects.buildSubBtn(driver).click();
		BuildSubmittalObject.SubmittalDisplay(driver);
		Thread.sleep(3000);
		BuildSubmittalObject.PassLoginID(driver).sendKeys(email);
		BuildSubmittalObject.PassPassword(driver).sendKeys(password);
		BuildSubmittalObject.LoginClick(driver).click();
		BuildSubmittalObject.waitForPageDisplay(wait); // y am i getting this even though the method is declared
		Thread.sleep(5000);
		BuildSubmittalObject.LoginValidation(driver);

	}
}
