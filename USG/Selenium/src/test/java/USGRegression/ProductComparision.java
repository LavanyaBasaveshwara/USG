package USGRegression;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.utils.DriverSetter;

import org.testng.Assert;

@Listeners(Listners.listnersClass.class)
public class ProductComparision {
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
//		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		//System.setProperty("webdriver.gecko.driver", "D:\\Drivers\\geckodriver.exe");
		driver = DriverSetter.getFireFoxDriver();
		driver.manage().window().maximize();
		
	}


	
	@Test(description="Test to verify if the product comparison page is launched", priority=1)
	public void ProdComp() throws InterruptedException {
		driver.get("https://www.usg.com/content/usgcom/en/design-studio/product-comparison-tool.html?category=ceilings");
		String pcTitle = driver.findElement(By.xpath("//h1[@class='competitor-comparison-tool__title']")).getText();
		Assert.assertEquals(pcTitle,"Ceilings Comparison Tool" );
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Armstrong')]")));
			
		driver.findElement(By.xpath("//label[contains(text(),'Armstrong')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[contains(text(),\"Do you know the competitor's item number?\")]")));
		
		driver.findElement(By.xpath("//label[contains(text(),'No')]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Ceiling Tiles and Panels')]")));
//		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[contains(text(),'Ceiling Tiles and Panels')]")).click();		
		Select Drop = new Select(driver.findElement(By.xpath("//select[@name='product-a']")));
		Drop.selectByIndex(1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='product-b']")));
		Select Drop2 = new Select(driver.findElement(By.xpath("//select[@name='product-b']")));
		Drop2.selectByIndex(1);
		Thread.sleep(2000);
		Select Drop3 = new Select(driver.findElement(By.xpath("//select[@name='product-c']")));
		Drop3.deselectByIndex(1);
		Assert.assertEquals("Competitor Comparison",driver.findElement(By.xpath("//h1[@class='cdp-header__title']")).getText());
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		
	}
}
