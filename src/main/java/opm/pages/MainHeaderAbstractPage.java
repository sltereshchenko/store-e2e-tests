package opm.pages;

import opm.Tab;
import opm.pages.checkout.CartPage;
import opm.pages.product.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverHelper;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class MainHeaderAbstractPage extends AbstractPage {
    protected static final String MAIN_MENU_XPATH = "//ul[@id='menu-main-menu']";
    private static final String TAB_XPATH = MAIN_MENU_XPATH + "//a[text()='%s']";

    @FindBy(xpath = "//*[@id='header_cart']//a[@title='Checkout']")
    protected WebElement cart;
    private Map<Tab, Supplier<MainHeaderAbstractPage>> tabs;

    protected MainHeaderAbstractPage(WebDriver driver) {
        super(driver);
        tabs = new EnumMap<>(Tab.class);
        tabs.put(Tab.HOME, () -> new HomePage(driver));
        tabs.put(Tab.ACCESSORIES, () -> new AccessoriesPage(driver));
        tabs.put(Tab.IMACS, () -> new IMacsPage(driver));
        tabs.put(Tab.IPADS, () -> new IPadsPage(driver));
        tabs.put(Tab.IPHONES, () -> new IPhonesPage(driver));
        tabs.put(Tab.IPODS, () -> new IPodsPage(driver));
        tabs.put(Tab.MACBOOKS, () -> new MacBooksPage(driver));
    }

    public MainHeaderAbstractPage switchOnTab(Tab tab) {
        moveMouseToTab(tab);
        WebDriverHelper.findElementByXpath(driver, String.format(TAB_XPATH, tab.getName()), PAGE_LOADING_TIMEOUT).click();
        MainHeaderAbstractPage page = tabs.get(tab).get();
        page.waitForPage();
        return page;
    }

    private void moveMouseToTab(Tab tab) {
        if (tab.getParentTab() == null) {
            WebDriverHelper.moveToElement(driver,
                    WebDriverHelper.findElementByXpath(driver, String.format(TAB_XPATH, tab.getName()), ELEMENT_APPEAR_TIMEOUT));
        } else
            moveMouseToTab(tab.getParentTab());
    }

    public CartPage navigateToCart() {
        cart.click();
        CartPage cartPage = new CartPage(driver);
        cartPage.waitForPage();
        return cartPage;
    }

}
