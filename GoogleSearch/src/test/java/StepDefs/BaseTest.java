package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class BaseTest {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }

    protected WebDriver initializeDriver() {
        // Initialize your WebDriver here
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // Getter method for other step definitions to access the driver
    public static WebDriver getDriver() {
        return driver;
    }
}