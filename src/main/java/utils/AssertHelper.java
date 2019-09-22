package utils;

import opm.product.CheckOutProduct;
import opm.product.IProduct;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssertHelper<A extends Assertion> {
    protected A customAssert;

    AssertHelper(A customAssert) {
        this.customAssert = customAssert;
    }

    public <T extends IProduct, T1 extends CheckOutProduct> void checkProducts(List<T1> actualProducts, Map<T,
            Integer> expProdWithQuantity, String place) {
        List<CheckOutProduct> expectedCheckOutProducts = new ArrayList();
        expProdWithQuantity.forEach((T actualProduct, Integer quantity) ->
                expectedCheckOutProducts.add(new CheckOutProduct(actualProduct, quantity)));
        checkProducts((List<CheckOutProduct>) actualProducts, expectedCheckOutProducts, place);
    }

    public <T extends IProduct> void checkProducts(List<T> actualProducts, List<T> expectedProducts, String place) {
        actualProducts.forEach((T actualProduct) -> {
            T expectedProduct = null;
            for (T p : expectedProducts)
                if (p.name().equals(actualProduct.name())) {
                    expectedProduct = p;
                    break;
                }
            if (expectedProduct instanceof CheckOutProduct && actualProduct instanceof CheckOutProduct)
                compareCheckOutProducts((CheckOutProduct) expectedProduct, (CheckOutProduct) actualProduct, place);
            else
                throw new IllegalArgumentException(String.format("Comparison of %s and %s are not implemented.",
                        actualProduct.getClass().getName(), expectedProduct.getClass().getName()));
        });
    }

    public void compareCheckOutProducts(final CheckOutProduct expectedProduct, final CheckOutProduct actualProduct, String place) {
        String expectedProductTitle = expectedProduct.name();
        String actualProductTitle = actualProduct.name();
        // compare title
        customAssert.assertEquals(actualProductTitle, expectedProductTitle, String.format("%s: Products are not same as expected", place));
        // compare current price
        customAssert.assertEquals(actualProduct.getCurrentPrice(), expectedProduct.getCurrentPrice(),
                String.format("%s: Product price for each '%s' is incorrect", place, actualProductTitle));
        // compare quantity
        customAssert.assertEquals(actualProduct.getQuantity(), expectedProduct.getQuantity(),
                String.format("%s: %s Quantity on Transaction Results page is incorrect", place, actualProductTitle));
        // Total price for each type of product
        customAssert.assertEquals(actualProduct.getTotalPrice(), expectedProduct.getTotalPrice(),
                String.format("%s: Total price for '%s' is incorrect", place, actualProductTitle));
    }

    public A actualAssert() {
        return this.customAssert;
    }
}
