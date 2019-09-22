package opm.product;

import utils.Converter;

public class Product implements IProduct {
    private String name;
    private Float currentPrice;
    private String currentPriceOnUI;

    public Product(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public Float getCurrentPrice() {
        return this.currentPrice;
    }

    public Product setCurrentPrice(String currentPrice) {
        this.currentPriceOnUI = currentPrice;
        this.currentPrice = Converter.priceToFloat(this.currentPriceOnUI);
        return this;
    }

    public String uiCurrentPrice() {
        return this.currentPriceOnUI;
    }

}
