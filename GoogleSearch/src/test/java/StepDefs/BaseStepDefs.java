package StepDefs;
import Actions.AutomationPractice.*;
import Actions.Google.GoogleActions;
import Actions.Google.SearchActions;
import org.openqa.selenium.WebDriver;


public abstract class BaseStepDefs {
    protected static WebDriver driver;

    public static  class Actions{

        public static CheckBoxActions checkBoxActions = new CheckBoxActions(driver);
        public static final DropdownActions dropdownActions = new DropdownActions(driver);
        public static final PracticeHomePageActions practiceHomePageActions = new PracticeHomePageActions(driver);

        public static GoogleActions googleActions = new GoogleActions();
        public static SearchActions searchActions = new SearchActions();
        public static final WindowActions windowActions = new WindowActions(driver);
        public static final TabActions tabActions = new TabActions(driver);

        public static void initializeActions(WebDriver webDriver) {
            driver = webDriver;
            checkBoxActions = new CheckBoxActions(driver);


        }

    }
}
