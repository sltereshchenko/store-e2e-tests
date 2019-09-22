package opm;

public enum Tab {
    HOME("Home", null),
    PRODUCT_CATEGORY("Product Category", null),
    ACCESSORIES("Accessories", PRODUCT_CATEGORY),
    IMACS("iMacs", PRODUCT_CATEGORY),
    IPADS("iPads", PRODUCT_CATEGORY),
    IPHONES("iPhones", PRODUCT_CATEGORY),
    IPODS("iPods", PRODUCT_CATEGORY),
    MACBOOKS("MacBooks", PRODUCT_CATEGORY);


    private String name;
    private Tab parentTab;

    Tab(String name, Tab parentTab) {
        this.name = name;
        this.parentTab = parentTab;
    }

    public Tab getParentTab() {
        return parentTab;
    }

    public String getName() {
        return name;
    }
}

