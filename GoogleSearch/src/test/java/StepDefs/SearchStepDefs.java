package StepDefs;

import Pages.GooglePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class SearchStepDefs {

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
        google.navigateToGoogle();
google.selectRejectAll();        }
        @When("^I enter a search term \"([^\"]*)\"$")
        public void iEnterASearchTerm(String searchTerm) {
        google.getSearch();
        google.enterSearchTerm(searchTerm);
        google.clickGoogleSearch();
        }
        @Then("^results relevant to the search term are returned$")
        public void resultsRelevantToTheSearchTermAreReturned() {
        google.displaySearchResults();
        google.navigateToGoogle();
        }
        @When("^I tap on Feeling lucky button$")
    public void iTapOnFeelingLuckyButton() {
       google.tapFeelingLuckyButton();

            }

        @Then("^Browser redirects to URL containing search term$")
    public void browserRedirectsToURLContainingSearchTerm() {
        google.feelingLuckySearchResults();
        }

}
