package StepDefs.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import StepDefs.Utils.ScenarioContext;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private final ScenarioContext context;

    // Remove @Inject annotation - PicoContainer will handle injection automatically
    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());
        // Setup logic
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Scenario failed: {}", scenario.getName());
        }
        log.info("Finished scenario: {}", scenario.getName());
        // Cleanup logic
    }
}