package StepDefs;
import Actions.AutomationPractice.*;
import Actions.Google.GoogleActions;
import Actions.Google.SearchActions;

public abstract class BaseStepDefs {


    protected static final class Actions{

        public static final GoogleActions googleActions = new GoogleActions();
        public static final SearchActions searchActions = new SearchActions();
        public static final WindowActions windowActions = new WindowActions();
        public static final TabActions tabActions = new TabActions();
        public static final CheckBoxActions checkBoxActions = new CheckBoxActions();
        public static final DropdownActions dropdownActions = new DropdownActions();
        public static final PracticeHomePageActions practiceHomePageActions = new PracticeHomePageActions();
    }
}
