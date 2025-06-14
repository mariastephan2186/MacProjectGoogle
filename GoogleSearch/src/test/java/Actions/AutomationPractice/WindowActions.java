package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

public class WindowActions {
    private AutomationPracticePage automationPracticePage;


    public WindowActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }
}
