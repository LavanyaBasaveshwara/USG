package helper.utils;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverSetter {
	
	public static final ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", 
				SystemUtils.IS_OS_MAC? "/Users/VRC1/selenium/chromedriver-v80" : "D:\\Drivers\\chromedriver.exe");
		return new ChromeDriver();
	}
	
	public static final FirefoxDriver getFireFoxDriver() {
		System.setProperty("webdriver.gecko.driver",
				SystemUtils.IS_OS_MAC? "/Users/VRC1/selenium/geckodriver" : "D:\\Drivers\\geckodriver.exe");
		return new FirefoxDriver();
	}

}
