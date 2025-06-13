package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;

import java.util.List;

public class CheckBoxActions {

    public void selectCheckBox(String optionNumber) {
        new AutomationPracticePage().selectCheckbox(optionNumber);

    }

    public void unselectCheckBox(String optionNumber) {
        new AutomationPracticePage().deselectCheckbox(optionNumber);
    }

    public boolean isCheckBoxSelected(String optionNumber) {
        return new AutomationPracticePage().isCheckboxSelected(optionNumber);
    }

    public List<String> getSelectedOption(){
        return new AutomationPracticePage().getSelectedCheckboxLabels();
    }

}
