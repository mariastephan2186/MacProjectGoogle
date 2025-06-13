package Actions.AutomationPractice;

import Pages.AutomationPractice.AutomationPracticePage;

public class DropdownActions {


    public void clickDropdown(){
        new AutomationPracticePage().clickDropdown();
    }


    public void iCanSelectAnOptionFromTheDropdown(String option) {
        new AutomationPracticePage().selectFromDropdown(option);

    }
}
