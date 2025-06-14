package StepDefs.AutomationPractice;

import Actions.AutomationPractice.CheckBoxActions;
import StepDefs.BaseStepDefs;
import Tests.AutomationPractice.CheckBoxTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import io.qameta.allure.*;

@Feature("CheckBox")
public class CheckBoxStepdefs extends BaseStepDefs {

    @Given("^I am on the Automation Practice home page$")
    public void iAmOnTheAutomationPracticeHomePage() {
        Actions.practiceHomePageActions.openHomePage();
        Actions.practiceHomePageActions.isPageLoaded();
        Actions.practiceHomePageActions.getPageTitle();
        CheckBoxTest.isUseronTheAutomationPracticeHomePage();
        Allure.step("I am on the Automation Practice home page");
    }


    @When("^I select more than one option$")
    public void iSelectMoreThanOneOption(String optionNumber) {
        Actions.practiceHomePageActions.openHomePage();
        Actions.checkBoxActions.selectCheckbox(optionNumber);
        Allure.step("I select more than one option");
    }

    @Then("^I can see multiple options can be selected$")
    public void iCanSeeMultipleOptionsCanBeSelected(String optionNumber) {
        Assert.assertTrue(Actions.checkBoxActions.isCheckBoxSelected(optionNumber),
                "Checkbox " + optionNumber + " is not selected");

    Allure.step("I can see multiple options can be selected");
    }

    @And("^I can also deselect the options$")
    public void iCanAlsoDeselectTheOptions(String optionNumber) {
        Actions.checkBoxActions.unselectCheckBox(optionNumber);
        Allure.step("I can also deselect the options");
    }
}
