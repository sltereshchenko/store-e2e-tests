package tests.purchase;

import opm.Tab;
import opm.contact.BillingContact;
import opm.contact.ContactBuilder;
import opm.contact.ContactType;
import opm.contact.ShippingContact;
import opm.pages.HomePage;
import opm.pages.checkout.CartPage;
import opm.pages.checkout.FinalPage;
import opm.pages.checkout.InfoPage;
import opm.pages.product.ProductsAbstractPage;
import opm.product.CheckOutProduct;
import opm.product.IProduct;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.UITestSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseProcessTest extends UITestSetup {

    @DataProvider(name = "products")
    public Object[][] getProductsMap() {
        Map accessoriesTabMap = new HashMap<String, Integer>();
        Map macbooksTabMap = new HashMap<String, Integer>();
        accessoriesTabMap.put("Magic Mouse", 1);
        macbooksTabMap.put("Magic Mouse", 2);
        macbooksTabMap.put("Apple 13-inch MacBook Pro", 1);
        return new Object[][]{
                {Tab.ACCESSORIES, accessoriesTabMap},
                {Tab.MACBOOKS, macbooksTabMap}
        };
    }

    /**
     * Test Case 1: (Parameterized, depends on input parameters: tab where to buy, products to buy)
     * case#1: buy 1 'Magic Mouse' on Accessories page
     * case#2: buy 2 'Magic Mouse' and 1 'Apple 13-inch MacBook Pro' on MacBooks page
     * - Open browser and Navigate to home page
     * - Go to some product category tab
     * - Parse and store product's data from product category tab
     * - Buy products
     * - Go to cart (checkout)
     * - Check that 'Your Cart' tab has expected products (same names, prices, total prices, quantities)
     * - Click Continue and go to 'Info' tab
     * - Fill out dummy email, shipping and billing data
     * - Click 'Purchase' and go to 'Final' tab (Transaction Results)
     * - Check that Transaction Results have expected products (same names, prices, total prices, quantities)
     * - Close browser
     */
    @Test(priority = 1, dataProvider = "products")
    public void purchaseProductTest(Tab tabWithProducts, Map<String, Integer> inputProductsMap) {
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();
        ProductsAbstractPage productsPage = (ProductsAbstractPage) homePage.switchOnTab(tabWithProducts);
        // parse information about products
        Map<IProduct, Integer> productsMap = new HashMap();
        productsPage.getProducts(inputProductsMap.keySet()).forEach(product -> productsMap.put(product, inputProductsMap.get(product.name())));
        // add products to cart
        productsMap.forEach(productsPage::addToCart);
        CartPage cartPage = productsPage.navigateToCart();
        List<CheckOutProduct> cartProducts = cartPage.getProducts();
        Assert.assertTrue(cartProducts.size() == productsMap.size(),
                String.format("We have '%s' products in  a cart! Expected is '%s'", cartProducts.size(), productsMap.size()));
        // check products on Cart Page
        assertHelper.checkProducts(cartProducts, productsMap, "Cart Page");
        InfoPage infoPage = cartPage.clickContinue();
        // populate contacts information
        infoPage.setEmail("st@mail.ca").populateContacts((ShippingContact) ContactBuilder.getDummyContact(ContactType.SHIPPING_CONTACT),
                (BillingContact) ContactBuilder.getDummyContact(ContactType.BILLING_CONTACT));
        FinalPage finalPage = infoPage.clickPurchase();
        // parse information about products
        List<CheckOutProduct> finalProducts = finalPage.getProducts();
        // check products on Final Page (Transaction Results)
        assertHelper.checkProducts(finalProducts, cartProducts, "Final Page");
        assertHelper.actualAssert().assertAll();
    }

}
