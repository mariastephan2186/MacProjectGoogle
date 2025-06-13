package StepDefs.AutomationPractice;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TabsStepdefs {
    @When("^I click on the button \"([^\"]*)\"$")
    public void iClickOnTheButton(String buttonName) {
    }

    @Then("I can see that a new tab is opened")
    public void iCanSeeThatANewTabIsOpened() {
    }

    @Given("I can switch to the new tab")
    public void iCanSwitchToTheNewTab() {
    }
}
