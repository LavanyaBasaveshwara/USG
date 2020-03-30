package USGRegression;

import org.openqa.selenium.By;
import helper.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import USGRegressionObjects.BuildSubmittalObject;
import USGRegressionObjects.HomePageObjects;

public class BuildSubmittalPage {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
	System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	wait=new WebDriverWait(driver, 30);
	driver.manage().window().maximize();
	driver.get("https://www.usg.com/content/usgcom/en.html");
	}
	
	@Test (priority =1, description = "Login test to Build Submittal")
	public void loginBuildSub() throws InterruptedException {
		HomePageObjects.waitForBuildSub_Btn(wait);
		HomePageObjects.buildSubBtn(driver).click();
		BuildSubmittalObject.SubmittalDisplay(driver);
		Thread.sleep(3000);
		BuildSubmittalObject.PassLoginID(driver).sendKeys(LoginIDPassword.loginID());
		BuildSubmittalObject.PassPassword(driver).sendKeys(LoginIDPassword.password());
		BuildSubmittalObject.LoginClick(driver).click();
		//BuildSubmittalObject.waitForPageDisplay(wait); 
		Thread.sleep(5000);
		BuildSubmittalObject.LoginValidation(driver);
		driver.findElement(By.xpath("//div[@id='fade']")).click();
	}
	
	@Test(priority=2, description = "add new project and verify if it is displayed")
	public void AddProject() throws InterruptedException {
		BuildSubmittalObject.AddNewProjButton(driver).click();
		//BuildSubmittalObject.SubmittalDocumentspage(driver);
		BuildSubmittalObject.SelectByCategoryDropDown(driver);
		Thread.sleep(10000);
		BuildSubmittalObject.SelectBySubcategyDropDown(driver);
		BuildSubmittalObject.SelectProducts(driver,wait);
		
	}
	
	@Test(priority=3, description = "Validate if teh project is created")
	public void ValidateProjCreated() {
		BuildSubmittalObject.ValidateProject(driver);
	}
	
}