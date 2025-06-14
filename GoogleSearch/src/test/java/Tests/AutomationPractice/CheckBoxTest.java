package Tests.AutomationPractice;

import Actions.AutomationPractice.CheckBoxActions;
import Actions.AutomationPractice.PracticeHomePageActions;
import Pages.AutomationPractice.AutomationPracticePage;
import StepDefs.AutomationPractice.CheckBoxStepdefs;
import StepDefs.BaseStepDefs;
import StepDefs.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CheckBoxTest {
    private static WebDriver driver;
    private static CheckBoxActions checkBoxActions;



    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        checkBoxActions = new CheckBoxActions(driver);
    }

    @Test
    public static void isUseronTheAutomationPracticeHomePage() {

        String expectedTitle = "Practice Page";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match expected");
    }
    @Test
    public static void isTheCheckBoxChecked() {
        checkBoxActions.selectCheckbox("1");
        checkBoxActions.isCheckBoxSelected("1");

    }

    @Test
    public static void isTheCheckBoxUnchecked() {
        checkBoxActions.selectCheckbox("1");
        checkBoxActions.unselectCheckBox("1");
        checkBoxActions.isCheckBoxSelected("1");
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
