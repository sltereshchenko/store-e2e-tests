package utils;

import opm.browser.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


public abstract class UITestSetup {

    protected AssertHelper<SoftAssert> assertHelper;
    protected SoftAssert softAssert = new SoftAssert();
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setupTest() {
        driver = WebDriverManager.getDriverInstance();
        assertHelper = new AssertHelper(softAssert);
    }

    @AfterMethod
    public void tearDownTest() {
        closeBrowser();
    }

    private void closeBrowser() {
        driver.close();
        WebDriverManager.setNull();
    }
}
