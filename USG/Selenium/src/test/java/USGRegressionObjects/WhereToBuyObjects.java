package USGRegressionObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhereToBuyObjects {
	public static String element;
	
	public String getPageTitleWhereToBuy(WebDriver driver) {
		
		element = driver.getTitle();
		return element;
	}

	

}
