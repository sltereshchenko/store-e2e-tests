package opm.pages.checkout;

import opm.pages.MainHeaderAbstractPage;
import org.openqa.selenium.WebDriver;
import utils.WebDriverHelper;

public abstract class CheckoutAbstractPage extends MainHeaderAbstractPage {
    protected static final String CHECKOUT_PAGE_XPATH = "//div[@id='checkout_page_container']";
    protected static final String SELECTED_PROGRESS_BAR_TAB_XPATH = "(//li[contains(@class, 'act')][contains(@class, '%s')][not(following-sibling::li[contains(@class, 'act')])])[1]";
    protected String tabID;

    protected CheckoutAbstractPage(WebDriver driver, String tabID) {
        super(driver);
        this.tabID = tabID;
    }

    public void waitForPage() {
        WebDriverHelper.findElementByXpath(driver, String.format(SELECTED_PROGRESS_BAR_TAB_XPATH, tabID), PAGE_LOADING_TIMEOUT);
    }
}
