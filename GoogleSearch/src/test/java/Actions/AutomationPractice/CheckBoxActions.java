package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckBoxActions {

    private final AutomationPracticePage automationPracticePage;

    public CheckBoxActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }



    public void unselectCheckBox(String checkbox) {
        int index = Integer.parseInt(checkbox) - 1;  // Convert "1" to 0-based index
        if (automationPracticePage.isCheckboxSelected(index)) {
            automationPracticePage.selectCheckbox(index);  // This will toggle it off if it's selected
        }    }

    public boolean isCheckBoxSelected(String optionNumber) {
        int index = Integer.parseInt(optionNumber) - 1;
        return automationPracticePage.isCheckboxSelected(index);    }

    public void selectCheckbox(String index) {
        int idx = Integer.parseInt(index) - 1;
        if (!automationPracticePage.isCheckboxSelected(idx)) {
            automationPracticePage.selectCheckbox(idx);
        }
    }

}
