import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helper.utils.DriverSetter;

public class WindowHandlingTest {

	WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        
        driver = DriverSetter.getChromeDriver();
        driver.get("http://guidebook.seleniumacademy.com/Window.html");
    }

    @Test
    public void handleWindow() {

        String firstWindow = driver.getWindowHandle();
        System.out.println("First Window Handle is: " + firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();

        String secondWindow = driver.getWindowHandle();
        System.out.println("Second Window Handle is: " + secondWindow);
        System.out.println("Number of Window Handles so for: "
                + driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
