package USGRegression;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

import USGRegressionObjects.*;
import Utile.CaptureScreenshot;

public class POHomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		wait=new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get("https://www.usg.com/content/usgcom/en.html");	
				
	}
	
	@AfterMethod
	public void teardown(ITestResult result) throws Exception {
		if (ITestResult.FAILURE==result.getStatus()) {
			CaptureScreenshot.takeSnapShot(driver,result.getName());
			driver.quit();
		}
		driver.quit();
		
	}
	
	
	
	@Test(priority=1 , description ="Test to validate if Where to Buy page is displayed on click of Where to Buy button")
	public void WhereToBuyDisplay() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='nav-utility__menu-link-text has-icon btn btn--small']")));
		//How to implement POM in this case?
		HomePageObjects.waitForWhereToBuy_Btn(wait);
		HomePageObjects.whereToBuy_Btn(driver).click();
		//wait.until(getTitle()!="USG | Home" );
		Thread.sleep(5000);
		String WTB = driver.getTitle();
		System.out.println(WTB);
		Assert.assertEquals(WTB,"USG | Where to Buy");
						
	}
	
	@Test(priority=2, description = "Test to validate the button Build a Submittal")
	public void BuildSubmittalDisplay() {
		HomePageObjects.waitForBuildSub_Btn(wait);
		HomePageObjects.buildSubBtn(driver).click();
		BuildSubmittalObject.SubmittalDisplay(driver);
		/* WebElement BSP = */ 
//		Assert.assertEquals(BSP.getText(), "Submittal");
				
	}
	
	@Test(priority=3, description = "Test to validate the Build a Submittal Page is displayed")
	
	public void BuildSubPageDisplay() throws InterruptedException {
		HomePageObjects.waitForBuildSub_Btn(wait);
		HomePageObjects.buildSubBtn(driver).click();
		BuildSubmittalObject.SubmittalDisplay(driver);
		Thread.sleep(3000);
		BuildSubmittalObject.PassLoginID(driver).sendKeys("");
		BuildSubmittalObject.PassPassword(driver).sendKeys("");
		BuildSubmittalObject.LoginClick(driver).click();
		BuildSubmittalObject.waitForPageDisplay(wait); // y am i getting this even though the method is declared
		Thread.sleep(5000);
		BuildSubmittalObject.LoginValidation(driver);

	}

}
