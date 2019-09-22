package opm.pages.product;

import opm.Tab;
import org.openqa.selenium.WebDriver;

public class MacBooksPage extends ProductsAbstractPage {

    public MacBooksPage(WebDriver driver) {
        super(driver, Tab.MACBOOKS);
    }
}