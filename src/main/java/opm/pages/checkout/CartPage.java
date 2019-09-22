package opm.pages.checkout;

import opm.product.CheckOutProduct;
import opm.product.IProduct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Converter;
import utils.WebDriverHelper;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends CheckoutAbstractPage implements IPageWithProductInfo {
    protected static final String PRODUCT_ROW_XPATH = "//tr[descendant::a[text()='%s']]";
    protected static final String PRICE_XPATH = "(%s/td[%s]//span)[last()]";
    protected static final String PRODUCT_CURRENT_PRICE_XPATH = String.format(PRICE_XPATH, PRODUCT_ROW_XPATH, 4);
    protected static final String PRODUCT_TOTAL_PRICE_XPATH = String.format(PRICE_XPATH, PRODUCT_ROW_XPATH, 5);
    protected static final String PRODUCT_QUANTITY_XPATH = PRODUCT_ROW_XPATH + "/td[3]//input[@name='quantity']";
    @FindBy(xpath = CHECKOUT_PAGE_XPATH + "//a//span[text()='Continue']")
    protected WebElement continueButton;
    @FindBy(xpath = "//tr/td[2]//a")
    protected List<WebElement> productTitleLinks;

    public CartPage(WebDriver driver) {
        super(driver, "cart");
    }

    public InfoPage clickContinue() {
        continueButton.click();
        InfoPage infoPage = new InfoPage(driver);
        infoPage.waitForPage();
        return infoPage;
    }

    public List<CheckOutProduct> getProducts() {
        List<CheckOutProduct> products = new ArrayList();

        getProductsTitles().forEach(title -> products.add(getProduct(title)));
        return products;
    }

    public CheckOutProduct getProduct(String productTitle) {
        CheckOutProduct product = new CheckOutProduct(productTitle);
        return (CheckOutProduct) product.setQuantity(Integer.parseInt(getQuantityForProduct(product)))
                .setTotalPrice(Converter.priceToFloat(getTotalPriceForProduct(product)))
                .setCurrentPrice(getCurrentPriceForProduct(product));
    }

    private List<String> getProductsTitles() {
        List<String> productsTitles = new ArrayList();
        productTitleLinks.forEach(a -> productsTitles.add(a.getText()));
        return productsTitles;
    }

    protected String getCurrentPriceForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_CURRENT_PRICE_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

    protected String getQuantityForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_QUANTITY_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getAttribute("value");
    }

    protected String getTotalPriceForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_TOTAL_PRICE_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

}
