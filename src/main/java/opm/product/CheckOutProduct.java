package opm.product;

public class CheckOutProduct extends Product {
    private Float totalPrice;
    private Integer quantity;

    public CheckOutProduct(String name) {
        super(name);
    }

    public CheckOutProduct(IProduct product, Integer quantity) {
        super(product.name());
        this.setCurrentPrice(product.uiCurrentPrice());
        this.quantity = quantity;
        this.totalPrice = this.quantity * getCurrentPrice();
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }

    public CheckOutProduct setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public CheckOutProduct setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
