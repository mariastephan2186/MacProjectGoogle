package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class BasePage {
        protected WebDriver driver;
        protected WebDriverWait wait;
        protected String mainWindow;

        // Constructor
        public BasePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            this.mainWindow = driver.getWindowHandle();
        }

    public BasePage() {
    };


    // Common wait methods
        protected WebElement waitForElement(By locator) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        protected WebElement waitForClickable(By locator) {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        // Window handling
        protected void switchToNewWindow() {
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        }

        protected void switchToMainWindow() {
            driver.switchTo().window(mainWindow);
        }

        // Common actions
        protected void click(By locator) {
            waitForClickable(locator).click();
        }

        protected void type(By locator, String text) {
            WebElement element = waitForElement(locator);
            element.clear();
            element.sendKeys(text);
        }

        protected String getText(By locator) {
            return waitForElement(locator).getText();
        }

        protected boolean isDisplayed(By locator) {
            try {
                return waitForElement(locator).isDisplayed();
            } catch (TimeoutException | NoSuchElementException e) {
                return false;
            }
        }

        // Frame handling
        protected void switchToFrame(By frameLocator) {
            WebElement frame = waitForElement(frameLocator);
            driver.switchTo().frame(frame);
        }

        protected void switchToDefaultContent() {
            driver.switchTo().defaultContent();
        }

        // Alert handling
        protected void acceptAlert() {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }

        protected void dismissAlert() {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();
        }

        // Scroll methods
        protected void scrollToElement(By locator) {
            WebElement element = waitForElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }

        // Get page state
        protected String getCurrentUrl() {
            return driver.getCurrentUrl();
        }

        protected String getTitle() {
            return driver.getTitle();
        }
    }

