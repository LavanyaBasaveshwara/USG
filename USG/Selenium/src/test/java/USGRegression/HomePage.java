package USGRegression;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;


public class HomePage {
	
	WebDriver driver;
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }

	
@BeforeTest
public void setup() {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	


	
@Test(description="Test to verify if the application is launched", priority=1)
public void LaunchBrowser() {
	driver.get("https://www.usg.com/content/usgcom/en.html");
	String Title = driver.getTitle();
	System.out.println("Title" + Title);
	
	String expected_title = "USG | Home";
	
	//driver.findElements(By.xpath("//h2[@class='our-company']"));
	
	Assert.assertEquals(Title,expected_title);
	
}



@Test(description="Test to verify the Acoustical page is displayed on click of Ceiling->Acoustical page. ", priority=2)
public void LaunchAcoustical() throws InterruptedException {
	driver.findElement(By.xpath("//a[@class='navbar__link has-dropdown-link'][contains(text(),'Ceilings')]")).click();
	List<WebElement> dropDownElements = driver.findElements(
			By.xpath("//li[@class='navbar__link-item has-dropdown active']//ul[@class='navbar__dropdown menu-dropdown menu-dropdown--columns-3 menu-dropdown--pull-left']"));
	Assert.assertFalse(dropDownElements.isEmpty());
	driver.findElement(By.xpath("//a[contains(text(),'Acoustical Panels')]")).click();
	//String TitleAcoustical = driver.getTitle();
	//System.out.println("AcousticalTitle" + TitleAcoustical);
	WebElement acousticalPageVar = driver.findElement(By.xpath("//h1[@class='hero__title']"));
	Assert.assertEquals(acousticalPageVar.getText(),"USG | Acoustical Ceiling Panels");
	
	
	
}
@Test(description="test to verify that the About USG page is displayed on click of link under Our Company. ", priority=3)
public void AboutUSGPageDisplay() throws InterruptedException {
	driver.get("https://www.usg.com/content/usgcom/en.html");
	WebDriverWait wait=new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'For 115 years USG has been an industry leader in i')]")));
	driver.findElement(By.xpath("//a[contains(text(),'For 115 years USG has been an industry leader in i')]")).click();
	Thread.sleep(5000);
	Set<String> ids = driver.getWindowHandles();
	System.out.println("VRC-Size of ids:"+ids.size());
    Iterator<String> it = ids.iterator();
    String parentId = it.next();
    String childId = it.next();
    driver.switchTo().window(childId);
    WebElement usgElement = driver.findElement(By.xpath("//h1[contains(text(),'About USG')]"));
	String Something = usgElement.getText();
	Assert.assertEquals(Something, "ABOUT USG");
	
}

@Test(description="Test to validate the display of Promotion page on click of SEE ALL PROMOTION.", priority=4)
public void PromotionPageDisplay() throws Exception
{
	driver.get("https://www.usg.com/content/usgcom/en.html");
	WebDriverWait wait=new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'For 115 years USG has been an industry leader in i')]")));
	driver.findElement(By.xpath("//a[contains(text(),'SEE ALL PROMOTIONS')]")).click();
	String promationAttribute = driver.findElement(By.xpath("//h1[contains(text(),'Current Promotions')]")).getText();
	Assert.assertEquals(promationAttribute, "CURRENT PROMOTIONS");
	//this.takeSnapShot(driver, "./Screenshots/CurrentPromotion.png") ; 
	
}

@Test(description="Test to validate the Login functinality",priority=5)
public void LoginValidation() throws InterruptedException
{
	driver.get("https://www.usg.com/content/usgcom/en.html");
	Thread.sleep(3000);
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
	
}



@AfterMethod
public void checkForFailure(ITestResult result) throws Exception {
	if(ITestResult.FAILURE==result.getStatus());
	{
		takeSnapShot(driver,"Screenshots\\"+result.getName()+".png");
	}
	
}

@AfterTest
public void tearDown() {
	driver.quit();	
}


}
