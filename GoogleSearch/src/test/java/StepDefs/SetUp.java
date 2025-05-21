package StepDefs;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import java.util.logging.Level;

public class SetUp {
    public static WebDriver setUpAndGetDriver(){
        return new ChromeDriver(getCapabilities());
    }

    private static ChromeOptions getCapabilities(){
        LoggingPreferences logPrefs=new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER,Level.ALL);
        logPrefs.enable(LogType.DRIVER,Level.ALL);

        ChromeOptions options= new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        options.addArguments("--enable-automation");
        options.addArguments("--enable-crash-reporter");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
