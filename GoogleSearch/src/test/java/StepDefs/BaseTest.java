package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    private static WebDriver driver;
    private static final String PRACTICE_URL = "https://automationpractice.com/";

    @Before
    public void setUp() {
        driver = SetUp.setUpAndGetDriver();
        driver.get(PRACTICE_URL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Getter method for other step definitions to access the driver
    public static WebDriver getDriver() {
        return driver;
    }
}