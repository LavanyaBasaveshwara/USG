package Lav.Bas.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizationTest {
	static WebDriver driver;
	
	
@BeforeTest
	
public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
@Test
@Parameters({"loginId","password"})

public static void LoginTest(String loginID, String password) throws InterruptedException {
	
	driver.get("https://www.usg.com/content/usgcom/en.html");
	Thread.sleep(5000);
	WebElement AccountDropdown = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
	AccountDropdown.click();
	driver.findElement(By.xpath("//a[@class='btn login-modal-trigger model-trigger']")).click();
	driver.findElement(By.xpath("//input[@id='account-form--login__email']")).sendKeys(loginID);
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
}
}
