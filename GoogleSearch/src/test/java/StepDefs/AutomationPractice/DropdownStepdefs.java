package StepDefs.AutomationPractice;

import StepDefs.BaseStepDefs;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DropdownStepdefs extends BaseStepDefs {
    @When("^I click on the Dropdown field$")
    public void iClickOnTheDropdownField(String option) {
        Actions.dropdownActions.selectDropdown(option);

    }

    @Then("I can select an option from the dropdown")
    public void iCanSelectAnOptionFromTheDropdown() {
        Actions.dropdownActions.iCanSelectAnOptionFromTheDropdown();
    }
}
