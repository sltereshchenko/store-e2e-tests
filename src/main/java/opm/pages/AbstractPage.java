package opm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public abstract class AbstractPage implements IPage {
    public static final long PAGE_LOADING_TIMEOUT = 25;
    public static final long TIMEOUT = 2;
    public static final long ELEMENT_APPEAR_TIMEOUT = 5;
    protected final Logger log = Logger.getLogger(AbstractPage.class.getName());
    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        init();
    }

    protected void init() {
        PageFactory.initElements(driver, this);
    }
}
