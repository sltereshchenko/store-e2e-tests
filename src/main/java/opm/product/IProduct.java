package opm.product;

public interface IProduct {
    String name();

    Float getCurrentPrice();

    IProduct setCurrentPrice(String currentPrice);

    String uiCurrentPrice();
}
