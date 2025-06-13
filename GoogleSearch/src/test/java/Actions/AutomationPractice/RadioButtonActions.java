package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;

public class RadioButtonActions {

    public void selectRadioButton(String buttonNumber){
        new AutomationPracticePage().selectRadioButton(buttonNumber);
    }

    public void isRadioButtonSelected(String buttonNumber){
        new AutomationPracticePage().isRadioButtonSelected(buttonNumber);
    }
}
