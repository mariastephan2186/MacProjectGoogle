package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

public class DropdownActions {

    private AutomationPracticePage automationPracticePage;

    public DropdownActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }

    public void clickDropdown(){

    }


    public void iCanSelectAnOptionFromTheDropdown(String option) {
       automationPracticePage.selectFromDropdown(option);
    }

    public void selectDropdown(String option) {

    }
}
