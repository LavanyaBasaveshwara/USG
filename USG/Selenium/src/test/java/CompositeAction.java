import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helper.utils.DriverSetter;

public class CompositeAction {
	WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        
        driver = DriverSetter.getChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
    }

    @Test
    public void shouldPerformCompositeAction() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("five"));

        // Add all the actions into the Actions builder.
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(one)
                .click(three)
                .click(five)
                .keyUp(Keys.CONTROL);

        // Generate the composite action.
        Action compositeAction = actions.build();

        // Perform the composite action.
        compositeAction.perform();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
