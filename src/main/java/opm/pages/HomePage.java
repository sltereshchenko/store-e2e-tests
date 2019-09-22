package opm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.StoreProperties;
import utils.WebDriverHelper;

public class HomePage extends MainHeaderAbstractPage {
    private static final String URL = StoreProperties.URL;

    @FindBy(xpath = MAIN_MENU_XPATH + "/li[contains(@class,'current_page_item')]//a[text()='Home']")
    protected WebElement selectedHomeTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage navigateTo() {
        log.info("Try to navigate on Home page:" + URL);
        driver.get(URL);
        waitForPage();
        return this;
    }

    public void waitForPage() {
        WebDriverHelper.waitForElementVisibility(driver, this.selectedHomeTab, PAGE_LOADING_TIMEOUT);
    }
}
