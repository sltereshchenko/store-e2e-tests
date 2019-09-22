package opm.pages.product;

import opm.Tab;
import opm.pages.MainHeaderAbstractPage;
import opm.product.IProduct;
import opm.product.Product;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.WebDriverHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ProductsAbstractPage extends MainHeaderAbstractPage {
    protected static final String HEADER_XPATH = "//header[contains(@class,'page-header')]//h1[text()='%s']";
    protected static final String PRODUCTS_DIVS_XPATH = "//div[contains(@class,'default_product_display') or contains(@class,'product_grid_item')]";
    protected static final String PRODUCT_DIV_XPATH = PRODUCTS_DIVS_XPATH + "[descendant::a[text()='%s']]";
    protected static final String PRODUCT_CURRENT_PRICE_XPATH = PRODUCT_DIV_XPATH + "//span[contains(@class,'currentprice')]";
    protected static final String PRODUCT_ADD_TO_CART_XPATH = PRODUCT_DIV_XPATH + "//input[@name='Buy']";
    protected static final String PRODUCT_LOADING_IMG_XPATH = PRODUCT_DIV_XPATH + "//div[contains(@style,'visibility')]//img[@title='Loading']";
    protected final Tab tab;

    protected ProductsAbstractPage(WebDriver driver, Tab tab) {
        super(driver);
        this.tab = tab;
    }

    public void waitForPage() {
        WebDriverHelper.findElementByXpath(driver, String.format(HEADER_XPATH, tab.getName()), PAGE_LOADING_TIMEOUT);
    }

    public Product getProduct(String productTitle) {
        Product product = new Product(productTitle);
        product.setCurrentPrice(getCurrentPriceForProduct(product));
        return product;
    }

    public List<IProduct> getProducts(Collection<String> productTitles) {
        List<IProduct> products = new ArrayList<>();
        productTitles.forEach((String name) -> products.add(this.getProduct(name)));
        return products;
    }

    protected String getCurrentPriceForProduct(IProduct product) {
        return WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_CURRENT_PRICE_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).getText();
    }

    public void addToCart(IProduct product, Integer quantity) {
        for (int i = 0; i < quantity; i++)
            addToCart(product);
    }

    public void addToCart(IProduct product) {
        WebDriverHelper.findElementByXpath(driver, String.format(PRODUCT_ADD_TO_CART_XPATH, product.name()),
                ELEMENT_APPEAR_TIMEOUT).click();
        try {
            WebDriverHelper.waitForElementInVisibility(driver, WebDriverHelper.findElementByXpath(driver,
                    String.format(PRODUCT_LOADING_IMG_XPATH, product.name()), TIMEOUT), 2 * ELEMENT_APPEAR_TIMEOUT);
        } catch (NoSuchElementException ignore) {
            log.warning("Loading image did not appear after Add To Cart click!");
        }
    }

}