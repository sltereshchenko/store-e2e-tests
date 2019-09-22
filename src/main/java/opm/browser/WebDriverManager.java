package opm.browser;

import opm.os.OSType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.StoreProperties;

public class WebDriverManager {
    private static WebDriver driver = null;

    public static WebDriver getDriverInstance() {
        String driverFileSuffix = StoreProperties.OS.getSuffix() + (StoreProperties.OS == OSType.WINDOWS ? ".exe" : "");
        // Supports Firefox v55 and higher
        String geckoDriverPath = "src/test/resources/gecko-drivers/geckodriver_" + driverFileSuffix;
        //Supports Chrome v61-63
        String chromeDriverPath = "src/test/resources/chrome-drivers/chromedriver_" + driverFileSuffix;
        if (driver == null) {
            switch (StoreProperties.BROWSER) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    driver = new ChromeDriver();
                    break;

                case FIREFOX:
                default:
                    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                    System.setProperty("webdriver.gecko.driver", geckoDriverPath);
                    driver = new FirefoxDriver();
                    break;

            }
        }
        return driver;
    }

    public static void setNull() {
        driver = null;
    }


    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome");

        private String name;

        Browsers(String name) {
            this.name = name;
        }

        public static Browsers getBrowser(String name) {
            for (Browsers browser : Browsers.values()) {
                if (browser.getName().equalsIgnoreCase(name))
                    return browser;
            }
            // Default browser if string input is incorrect
            return FIREFOX;
        }

        public String getName() {
            return name;
        }
    }

}
