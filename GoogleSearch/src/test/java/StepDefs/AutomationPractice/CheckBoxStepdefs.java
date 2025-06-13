package StepDefs.AutomationPractice;

import Actions.AutomationPractice.CheckBoxActions;
import StepDefs.BaseStepDefs;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckBoxStepdefs extends BaseStepDefs {
    @Given("^I am on the Automation Practice home page$")
    public void iAmOnTheAutomationPracticeHomePage() {
   Actions.practiceHomePageActions.openHomePage();
   Actions.practiceHomePageActions.isPageLoaded();
   Actions.practiceHomePageActions.getPageTitle();

    }

    @When("^I select more than one option$")
    public void iSelectMoreThanOneOption(String optionNumber) {
        Actions.checkBoxActions.selectCheckBox(optionNumber);
    }

    @Then("^I can see multiple options can be selected$")
    public void iCanSeeMultipleOptionsCanBeSelected(String optionNumber) {
        Actions.checkBoxActions.isCheckBoxSelected(optionNumber);
    }

    @And("^I can also deselect the options$")
    public void iCanAlsoDeselectTheOptions(String optionNumber) {
        Actions.checkBoxActions.unselectCheckBox(optionNumber);
    }
}
