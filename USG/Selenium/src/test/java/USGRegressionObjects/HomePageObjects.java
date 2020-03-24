package USGRegressionObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObjects {
	
	private static final By WHERE_TO_BUY_BUTTON = By.xpath("//span[@class='nav-utility__menu-link-text has-icon btn btn--small']");
	private static final By BUILD_SUB_BUTTON = By.xpath("//span[contains(text(),'Build a Submittal')]");
	public static WebElement element;
	
	public static WebElement whereToBuy_Btn(WebDriver driver) {
		element = driver.findElement(WHERE_TO_BUY_BUTTON);
		return element;
	}

	public static void waitForWhereToBuy_Btn(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(WHERE_TO_BUY_BUTTON));
		
		}

	public static void waitForBuildSub_Btn(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(BUILD_SUB_BUTTON));
	}
	
	public static WebElement buildSubBtn(WebDriver driver) {
		element = driver.findElement(BUILD_SUB_BUTTON);
		return element;
	}
	
	
}
