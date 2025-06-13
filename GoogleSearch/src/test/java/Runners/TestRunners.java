package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

public class TestRunners {
    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {
                    "StepDefs.AutomationPractice",
                    "StepDefs.Hooks"

            },
            plugin = {
                    "pretty",
                    "html:target/cucumber-reports/cucumber.html",
                    "json:target/cucumber-reports/cucumber.json"
            },
            tags = "@regression or @smoke"
    )
    public class TestRunner {

    }
}
