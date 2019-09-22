package utils;

public class Converter {
    private Converter() {
    }

    public static float priceToFloat(String price) {
        return Float.valueOf(price.replace("$", ""));
    }
}
