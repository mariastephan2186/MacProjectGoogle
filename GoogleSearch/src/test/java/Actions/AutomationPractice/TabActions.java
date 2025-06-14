package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

public class TabActions {

    private AutomationPracticePage automationPracticePage;

    public TabActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }
    public void openNewTab(){
         automationPracticePage.openNewTab();
    }
}
