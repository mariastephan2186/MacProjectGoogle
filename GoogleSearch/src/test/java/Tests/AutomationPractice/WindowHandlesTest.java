package Tests.AutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowHandlesTest {
    ChromeDriver driver;
    String mainWindowHandle;
    String newWindowHandle;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainWindowHandle = driver.getWindowHandle();
    }

    @Test
    public void handleMultipleWindows(){
        WebElement openWindow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='openwindow']")));

        openWindow.click();
       Set<String> allWindowHandles =driver.getWindowHandles();
       for(String handle:allWindowHandles){
           if(!handle.equals(mainWindowHandle)){
               newWindowHandle = handle;
               driver.switchTo().window(handle);
               String expectedTitle = "QAClick Academy - A Testing Academy to Learn, Earn and Shine";
               String actualTitle = driver.getTitle();
               Assert.assertEquals(actualTitle,expectedTitle);
               driver.close();
               driver.switchTo().window(mainWindowHandle);
           }
       }
       //Switch back to the main window
        driver.switchTo().window(mainWindowHandle);
        System.out.println("Switched back to main window");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
