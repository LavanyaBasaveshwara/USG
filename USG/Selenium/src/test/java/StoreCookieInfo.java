import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helper.utils.DriverSetter;

public class StoreCookieInfo {
	WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        
        driver = DriverSetter.getChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
    }

    @Test
    public void storeCookies() {
        driver.findElement(By.id("email")).sendKeys("user@seleniumacademy.com");
        driver.findElement(By.id("pass")).sendKeys("tester");
        driver.findElement(By.id("send2")).submit();

        File dataFile = new File("./target/browser.data");
        try {
            dataFile.delete();
            dataFile.createNewFile();
            FileWriter fos = new FileWriter(dataFile);
            BufferedWriter bos = new BufferedWriter(fos);
            for (Cookie ck : driver.manage().getCookies()) {
                bos.write((ck.getName() + ";" + ck.getValue() + ";" + ck.
                        getDomain()
                        + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.
                        isSecure()));
                bos.newLine();
            }
            bos.flush();
            bos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
