package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

public class RadioButtonActions {
    private AutomationPracticePage automationPracticePage;

    public RadioButtonActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }
    public void selectRadioButton(String buttonNumber){
        automationPracticePage.selectRadioButton(buttonNumber);
    }

    public void isRadioButtonSelected(String buttonNumber){
automationPracticePage.isRadioButtonSelected(buttonNumber);
    }
}
