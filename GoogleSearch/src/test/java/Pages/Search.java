package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search {
    private static final By SEARCH_FIELD = By.id("APjFqb");
    private static final By SEARCH_RESULTS = By.xpath("//a/h3");
    private static final By FEELING_LUCKY_BUTTON = By.id("gbqfbb");
    private static final By REJECT_ALL = By.id("W0wltc");
    private WebDriver driver;

    public Search(WebDriver driver) {
        this.driver = driver;
    }

}
