package USGRegressionObjects;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuildSubmittalObject {
	
	private static final By SubmittalScreen = By.xpath("//span[contains(text(),'Submittal')]");
	public static WebElement element;
	//WebDriverWait wait=new WebDriverWait(driver, 30);
	
	public static WebElement SubmittalDisplay(WebDriver driver) {
		element = driver.findElement(SubmittalScreen);
		return element;
	}
	
	public static WebElement PassLoginID(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='email']"));
		return element;
	}
	
	public static WebElement PassPassword(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='password']"));
		return element;
	}

	public static WebElement LoginClick(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@class='button login-button']"));
		return element;
	}
	
	public static void LoginValidation(WebDriver driver) {
		driver.findElement(By.xpath("//button[contains(text(),'M')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Current Project')]"));
		String title = driver.getTitle();
		Assert.assertEquals("Submittal Prep Tool", title);
	}
	
	public static void waitForPageDisplay(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'M')]")));
	}
	
	public static WebElement AddNewProjButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@class='button saved-submittals-header-button usg-icon-plus-alt usg-icon']"));
		return element;
	}
	
	public static void SubmittalDocumentspage(WebDriver driver) {
		String element1 = driver.findElement(By.xpath("//input[@class='title-input small-7']")).getText();
		System.out.println(element1);
		Assert.assertEquals(element1, "");
	}
	
	public static void SelectByCategoryDropDown (WebDriver driver) {
		WebElement testDropDown = driver.findElement(By.xpath("//select[@class='category-select']"));
		Select dropdown = new Select(testDropDown);  
		dropdown.selectByIndex(1);
	}
	
	public static void SelectBySubcategyDropDown(WebDriver driver) {
		WebElement testDropDown = driver.findElement(By.xpath("//select[@class='subcategory-select']"));
		Select dropdown = new Select (testDropDown);
		dropdown.selectByIndex(1);
	}
	
	public static void SelectProducts(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//main//li[1]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='on']//li[1]//button[1]")));
		driver.findElement(By.xpath("//li[@class='on']//li[1]//button[1]")).click();		
		//How to declare 2 arguments in the function
		driver.findElement(By.xpath("//li[@class='on']//li[2]//button[1]")).click();
		driver.findElement(By.xpath("//li[@class='tile']//p[contains(text(),'USG Donn® Brand Suspension Systems Accessory Data')]"));
		driver.findElement(By.xpath("//li[@class='tile']//p[contains(text(),'USG Ceiling Cloud Suspension Brace Technical Guide')]"));
	}
	
	public static void ValidateProject(WebDriver driver) {
		driver.findElement(By.xpath("//a[@class='form-nav-menu-item small-4 columns button active']//div[@class='icon']")).click();
		driver.findElement(By.xpath("//form[@name='submittalDetailsForm']//button[@class='button is-visible'][contains(text(),'Edit')]")).click();
		driver.findElement(By.xpath("//input[@id='submittalName']")).clear();
		driver.findElement(By.xpath("//input[@id='submittalName']")).sendKeys("Project1");
		driver.findElement(By.xpath("//button[@class='button done is-visible']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'M')]")).click();
		driver.findElement(By.xpath("//li[3]//a[1]")).click();
		String Proj = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/ul[1]/li[2]/div[1]/h3[1]")).getText();
		Assert.assertEquals("Project1", Proj);
	}
}
	