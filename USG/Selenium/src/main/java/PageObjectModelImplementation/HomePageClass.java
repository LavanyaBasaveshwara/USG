package PageObjectModelImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageClass {
	
	private static WebElement element;

	public static WebElement search_bar_input_field(WebDriver driver) {
		
	    element = driver.findElement(By.xpath("//input[@id='tags']"));
		return element;		
	}
	
	public static WebElement search_button(WebDriver driver) {
		
		element = driver.findElement(By.xpath("//span[@class='icon-search']"));
		return element;
	}
	
	
}
