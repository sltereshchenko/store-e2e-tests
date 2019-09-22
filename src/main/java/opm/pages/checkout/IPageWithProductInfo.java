package opm.pages.checkout;

import opm.product.CheckOutProduct;

import java.util.List;

public interface IPageWithProductInfo {
    List<CheckOutProduct> getProducts();

    CheckOutProduct getProduct(String productTitle);
}
