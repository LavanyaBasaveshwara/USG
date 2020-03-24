package POMTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectModelImplementation.HomePageClass;

public class searchfunctinality {
	
	private static WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@Test
public void searchResultVerification() {
	driver.get("https://www.usg.com/content/usgcom/en.html");
	HomePageClass.search_bar_input_field(driver).sendKeys("Acousticals");
	HomePageClass.search_button(driver).sendKeys(Keys.RETURN);
		
}
}
