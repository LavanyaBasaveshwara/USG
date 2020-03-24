package Lav.Bas.Selenium;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ValueTest {
	
	WebDriver driver;
	
@BeforeTest
public void setup() {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	

@Test(description="Test to validate the Login functinality",priority=5)
public void LoginValidation() throws InterruptedException
{
	driver.get("https://www.usg.com/content/usgcom/en.html");
//	Thread.sleep(3000);
	WebDriverWait wait=new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PROD-2")));
	WebElement AccountDropdown = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
	AccountDropdown.click();
//	AccountDropdown.selectByVisibleText('Profile').click();
	driver.findElement(By.xpath("//a[@class='btn login-modal-trigger model-trigger']")).click();
	driver.findElement(By.xpath("//input[@id='account-form--login__email']")).sendKeys("");
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("");
	Thread.sleep(3000);
	driver.findElement(By.id("submitButton")).click();
	String welcome = driver.findElement(By.xpath("//p[contains(text(),'Welcome back! You are now signed in.')]")).getText();
	Assert.assertEquals(welcome,"Welcome back! You are now signed in.");
	driver.findElement(By.xpath("//div[@class='account-form account-form--login']//a[@class='btn close-model'][contains(text(),'Back to the site')]")).click();
	driver.findElement(By.xpath("//span[contains(text(),'Build a Submittal')]")).click();
	String subPrepName = driver.findElement(By.xpath("//h2[contains(text(),'Lavanya')]")).getText();
	Assert.assertEquals(subPrepName,"LAVANYA");
	Thread.sleep(3000);
	Select dropProj = new Select (driver.findElement(By.xpath("//div[@id='projectFilter']//select")));
	dropProj.selectByVisibleText("A - Z");
	Thread.sleep(3000);
}

@AfterTest
public void tearDown() {
	driver.quit();	
}


}


