package Actions.AutomationPractice;
import Pages.AutomationPractice.AutomationPracticePage;
import org.openqa.selenium.WebDriver;

public class PracticeHomePageActions   {
    private AutomationPracticePage automationPracticePage;

    public PracticeHomePageActions(WebDriver driver) {
        this.automationPracticePage = new AutomationPracticePage(driver);
    }
    public void openHomePage(){
automationPracticePage.openHomePage();    }

    public String getPageTitle(){
        return automationPracticePage.getPageTitle();
    }

    public boolean isPageLoaded(){
        return automationPracticePage.isPageLoaded();
    }
}
