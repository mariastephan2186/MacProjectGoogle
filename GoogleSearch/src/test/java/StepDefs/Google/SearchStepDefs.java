package StepDefs.Google;

import Actions.Google.GoogleActions;
import Actions.Google.SearchActions;
import Pages.Google.GooglePage;
import StepDefs.BaseStepDefs;
import StepDefs.SetUp;
import Tests.Google.GoogleResultsTest;
import Tests.Google.SearchTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class SearchStepDefs extends BaseStepDefs {

    GooglePage  google;
    private WebDriver driver;

    @Before
            public void setUp() {
        driver = SetUp.setUpAndGetDriver();
        google = new GooglePage(driver);
    }

    @After
            public void tearDown() {
        driver.quit();
    }


        @Given("^I am on the Google UK homepage$")
    public void iAmOnTheGoogleUKHomepage() {
        GoogleActions.navigateToGoogle();
            GoogleActions.selectRejectAll();

    }
        @When("^I enter a search term \"([^\"]*)\"$")
        public void iEnterASearchTerm(String searchTerm) {
        SearchActions.getSearch();
        SearchActions.enterSearchTerm(searchTerm);
        SearchActions.clickGoogleSearch();
        }
        @Then("^results relevant to the search term are returned$")
        public void resultsRelevantToTheSearchTermAreReturned() {
        GoogleActions.displaySearchResults();
        GoogleActions.navigateToGoogle();
        SearchTest.resultsRelevantToTheSearchTermAreReturned();
        }
        @When("^I tap on Feeling lucky button$")
    public void iTapOnFeelingLuckyButton() {
       GoogleActions.tapFeelingLuckyButton();

            }

        @Then("^Browser redirects to URL containing search term$")
    public void browserRedirectsToURLContainingSearchTerm() {
        GoogleActions.viewFeelingLuckySearchResults();

            GoogleResultsTest.viewFeelingLuckySearchResults();
        }
}
