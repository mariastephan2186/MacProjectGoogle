package Pages.Google;

import Pages.BaseGooglePage;
import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchPage extends BaseGooglePage {
    private static final By SEARCH_FIELD = By.id("APjFqb");
    private static final By SEARCH_RESULTS = By.xpath("//a/h3");
    private static final By FEELING_LUCKY_BUTTON = By.id("gbqfbb");
    private static final By REJECT_ALL = By.id("W0wltc");

    private static final By DROPDOWN = By.id("gbqfqw");
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        WebElement dropdown = driver.findElement(DROPDOWN);
        Select select = new Select(dropdown);
        select.selectByVisibleText("English");
        select.selectByValue("tet");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "English");
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(SEARCH_FIELD));
    }
}
