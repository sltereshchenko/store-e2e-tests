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

public class FinalPage extends CheckoutAbstractPage implements IPageWithProductInfo {

    protected static final String PRODUCT_ROW_XPATH = "//tr[descendant::td[text()='%s']]";
    protected static final String VALUE_XPATH = "%s/td[%s]";
    protected static final String PRODUCT_PRICE_XPATH = String.format(VALUE_XPATH, PRODUCT_ROW_XPATH, 2);
    protected static final String PRODUCT_TOTAL_PRICE_XPATH = String.format(VALUE_XPATH, PRODUCT_ROW_XPATH, 4);
    protected static final String PRODUCT_QUANTITY_XPATH = String.format(VALUE_XPATH, PRODUCT_ROW_XPATH, 3);
    @FindBy(xpath = "//tbody//td[1]")
    protected List<WebElement> productTitles;

    public FinalPage(WebDriver driver) {
        super(driver, "final");
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
        productTitles.forEach(a -> productsTitles.add(a.getText()));
        return productsTitles;
    }

    protected String getCurrentPriceForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_PRICE_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

    protected String getQuantityForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_QUANTITY_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

    protected String getTotalPriceForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_TOTAL_PRICE_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

}
