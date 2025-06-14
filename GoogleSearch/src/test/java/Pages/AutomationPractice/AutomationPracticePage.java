package Pages.AutomationPractice;

import Pages.BasePage;
import StepDefs.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AutomationPracticePage extends BasePage {
    private static final String BASE_URL = "https://rahulshettyacademy.com/AutomationPractice/";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Constructors
    public AutomationPracticePage(WebDriver driver) {
        super(driver);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        System.out.println("Automation Practice Page loaded");
    }



    //Locators - List of locators on the Automation Practice Page

    // Radio Buttons
    private final By radioButton1 = By.cssSelector("input[value='radio1']");
    private final By radioButton2 = By.cssSelector("input[value='radio2']");
    private final By radioButton3 = By.cssSelector("input[value='radio3']");

    // Suggestion Class
    private final By countryAutocomplete = By.id("autocomplete");

    // Dropdown
    private final By dropdownSelect = By.id("dropdown-class-example");

    // Checkboxes
    private static final By checkBox1 = By.id("checkBoxOption1");
    private final By checkBox2 = By.id("checkBoxOption2");
    private final By checkBox3 = By.id("checkBoxOption3");
    private final By checkboxes = By.cssSelector("input[type='checkbox']");

    // Window/Tab Buttons
    private final By openWindowButton = By.id("openwindow");
    private final By openTabButton = By.id("opentab");

    // Alert Elements
    private final By nameInput = By.id("name");
    private final By alertButton = By.id("alertbtn");
    private final By confirmButton = By.id("confirmbtn");

    // Element Displayed/Hidden fields
    private final By displayedTextField = By.id("displayed-text");
    private final By hideButton = By.id("hide-textbox");
    private final By showButton = By.id("show-textbox");

    // Mouse Hover elements
    private final By mouseHoverButton = By.id("mousehover");
    private final By mouseHoverOptions = By.cssSelector(".mouse-hover-content a");

    //Methods for verifying if page is displayed
    // Utility Methods

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return isDisplayed(displayedTextField);
    }


    // Methods for Radio Buttons
    public void selectRadioButton(String buttonNumber) {
        switch (buttonNumber) {
            case "1" -> click(radioButton1);
            case "2" -> click(radioButton2);
            case "3" -> click(radioButton3);
            default -> throw new IllegalArgumentException("Invalid radio button number");
        }
    }

    public void isRadioButtonSelected(String buttonNumber) {
        switch (buttonNumber) {
            case "1" -> isSelected(radioButton1);
            case "2" -> isSelected(radioButton2);
            case "3" -> isSelected(radioButton3);
            default -> throw new IllegalArgumentException("Invalid radio button number");
        }
    }



    private void isSelected(By radioButton1) {
        if (waitForElement(radioButton1).isSelected()) {
            System.out.println("Radio button is selected");
        } else {
            System.out.println("Radio button is not selected");
        }
    }

    // Methods for Suggestion Class
    public void typeCountry(String countryName) {
        type(countryAutocomplete, countryName);
    }

    // Methods for Dropdown
    public void selectFromDropdown(String option) {
        WebElement dropdown = waitForElement(dropdownSelect);
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

    public void clickDropdown() {
        WebElement dropdown = waitForElement(dropdownSelect);
        dropdown.click();
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().equals("Option 2")) {
                option.click();
                break;
            }
        }
    }

    // Methods for Window Handling

    // Methods for Alerts
    public void triggerAlert(String name) {
        type(nameInput, name);
        click(alertButton);
    }

    public void triggerConfirm(String name) {
        type(nameInput, name);
        click(confirmButton);
    }

    // Methods for Display fields Example
    public void hideElement() {
        click(hideButton);
    }

    public void showElement() {
        click(showButton);
    }

    public boolean isElementDisplayed() {
        return isDisplayed(displayedTextField);
    }

    // Method for Mouse Hover
    public void performMouseHover() {
        WebElement element = waitForElement(mouseHoverButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void clickMouseHoverOption(String optionText) {
        performMouseHover();
        List<WebElement> options = driver.findElements(mouseHoverOptions);
        for (WebElement option : options) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }


    // Checkbox Methods
    public void selectCheckbox(int index) {
        List<WebElement> boxes = driver.findElements(checkboxes);
        if (index >= 0 && index < boxes.size()) {
            boxes.get(index).click();
        }
    }


    public void deselectCheckbox(String optionNumber) {
        By selectedCheckBoxLocator = By.id("checkBoxOption" + optionNumber);
        WebElement checkbox = waitForElement(selectedCheckBoxLocator);
        if (checkbox.isSelected()) {
            click(selectedCheckBoxLocator);
        }
    }

    public boolean isCheckboxSelected(int index) {
        By checkboxLocator = By.xpath("//input[@type='checkbox']");
        List<WebElement> checkboxes = driver.findElements(checkboxLocator);
        if (index >= 0 && index < checkboxes.size()) {
            return checkboxes.get(index).isSelected();
        }
        throw new IllegalArgumentException("Checkbox index out of bounds: " + index);
    }




    public void openNewTab() {
    }
}






