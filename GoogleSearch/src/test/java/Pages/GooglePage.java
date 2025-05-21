package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GooglePage {
    private static final By SEARCH_FIELD = By.id("APjFqb");
    private static final By SEARCH_RESULTS = By.xpath("//a/h3");
    private static final By FEELING_LUCKY_BUTTON = By.id("gbqfbb");
   private static final By REJECT_ALL = By.id("W0wltc");

    private WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeClickable(By locator)   {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        return (wait.until(ExpectedConditions.elementToBeClickable(locator)));
    }
//method to navigate to google
    public void navigateToGoogle() {
        driver.manage().window().maximize();
        driver.get("https://www.google.co.uk/");
    }
    //Method to tap reject all  button
    public void selectRejectAll() {
        Actions actions = new Actions(driver);
actions.moveToElement(driver.findElement(REJECT_ALL));
        waitForElementToBeClickable(REJECT_ALL).click();
    }

        public WebElement getSearch() {
        return driver.findElement(SEARCH_FIELD);
    }
    public void enterSearchTerm(String searchTerm) {
        getSearch().sendKeys(searchTerm);
    }
    public void displaySearchResults() {
       List<WebElement> resultHeaders= driver.findElements(SEARCH_RESULTS);
       for(WebElement resultHeader: resultHeaders){
           assertThat(resultHeader.getText()).as("Search results contain search term").contains("BBC");
       }
    }

//Search
    public void feelingLuckySearchResults() {
        String url = driver.getCurrentUrl();
        assertThat(url).contains(String.valueOf(getSearch().getText()));
    }

    public void tapFeelingLuckyButton() {
        waitForElementToBeClickable(FEELING_LUCKY_BUTTON).click();
    }

    public void clickGoogleSearch() {
        driver.findElement(SEARCH_FIELD).sendKeys(Keys.ENTER);
    }
}
