package Actions.AutomationPractice;
import Pages.AutomationPractice.AutomationPracticePage;

public class PracticeHomePageActions   {
    public void openHomePage(){
        new AutomationPracticePage().openHomePage();
    }

    public String getPageTitle(){
        return new AutomationPracticePage().getPageTitle();
    }

    public boolean isPageLoaded(){
        return new AutomationPracticePage().isPageLoaded();
    }
}
