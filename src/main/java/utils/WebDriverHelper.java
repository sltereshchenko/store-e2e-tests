package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverHelper {
    protected static final Logger LOG = Logger.getLogger(WebDriverHelper.class.getName());

    private WebDriverHelper() {
    }

    public static void waitForElementVisibility(final WebDriver webDriver, final WebElement webElement, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementInVisibility(final WebDriver webDriver, final WebElement webElement, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitForURL(final WebDriver webDriver, final String url, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.urlToBe(url));
    }

    public static void waitNextAction(final WebDriver webDriver, final long timeOutSeconds) {
        webDriver.manage().timeouts().implicitlyWait(timeOutSeconds, SECONDS);
    }

    public static WebElement findElementByXpath(final WebDriver webDriver, final String xpath, final long timeOutSeconds) {
        waitNextAction(webDriver, timeOutSeconds);
        return webDriver.findElement(By.xpath(xpath));
    }

    public static WebElement findElementByID(final WebDriver webDriver, final String id, final long timeOutSeconds) {
        waitNextAction(webDriver, timeOutSeconds);
        return webDriver.findElement(By.id(id));
    }

    public static void moveToElement(final WebDriver webDriver, final WebElement webElement) {
        (new Actions(webDriver)).moveToElement(webElement).build().perform();
    }


}
