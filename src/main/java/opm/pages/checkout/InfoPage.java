package opm.pages.checkout;

import opm.contact.BillingContact;
import opm.contact.ContactType;
import opm.contact.IContact;
import opm.contact.ShippingContact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverHelper;

public class InfoPage extends CheckoutAbstractPage {

    protected static final String TABLE_INPUT_XPATH = "//tr//input[@placeholder='%s']";
    protected static final String TABLE_TEXT_AREA_XPATH = "//tr//textarea[@placeholder='%s']";
    protected static final String TABLE_DROPDOWN_XPATH = "//tr[child::td[contains(.,'%s')]]//select";
    protected static final String DROPDOWN_OPTION_XPATH = "/option[text()='%s']";
    private static final String BILLING_TABLE_XPATH = "//table[contains(@class,'table-1')]";
    private static final String SHIPPING_TABLE_XPATH = "//table[contains(@class,'table-2')]";
    @FindBy(xpath = CHECKOUT_PAGE_XPATH + "//input[@placeholder='Email']")
    WebElement emailInput;

    @FindBy(xpath = CHECKOUT_PAGE_XPATH + "//input[@value='Purchase']")
    WebElement purchaseButton;

    public InfoPage(WebDriver driver) {
        super(driver, "info");
    }

    public InfoPage populateBillingContact(BillingContact contact) {
        return populateContact(contact).setPhone(contact.getPhone());
    }

    public InfoPage populateShippingContact(ShippingContact contact) {
        return populateContact(contact);
    }

    public InfoPage populateContacts(ShippingContact shippingContact, BillingContact billingContact) {
        return populateShippingContact(shippingContact).populateBillingContact(billingContact);
    }

    protected InfoPage populateContact(IContact contact) {
        ContactType contactType = contact.getContactType();
        return setFirstName(contact.getFirstName(), contactType).setLastName(contact.getLastName(), contactType)
                .setAddress(contact.getAddress(), contactType).setCity(contact.getCity(), contactType)
                .setRegion(contact.getRegion(), contactType).setPostalCode(contact.getPostalCode(), contactType)
                .setCountry(contact.getCountry(), contactType);
    }

    protected String getTableXpath(ContactType type) {
        if (type == ContactType.BILLING_CONTACT)
            return BILLING_TABLE_XPATH;
        if (type == ContactType.SHIPPING_CONTACT)
            return SHIPPING_TABLE_XPATH;
        throw new IllegalArgumentException(String.format("Table Locator for %s ContactType is not implemented.", type.name()));
    }

    protected void setInputValueInContactTable(String title, String value, ContactType type) {
        WebElement element = WebDriverHelper.findElementByXpath(driver, getTableXpath(type) + String.format(TABLE_INPUT_XPATH, title),
                ELEMENT_APPEAR_TIMEOUT);
        WebDriverHelper.waitForElementVisibility(driver, element, TIMEOUT);
        element.clear();
        element.sendKeys(value);
    }

    protected void setTextAreaValueInContactTable(String title, String value, ContactType type) {
        WebElement element = WebDriverHelper.findElementByXpath(driver, getTableXpath(type) + String.format(TABLE_TEXT_AREA_XPATH, title),
                ELEMENT_APPEAR_TIMEOUT);
        WebDriverHelper.waitForElementVisibility(driver, element, TIMEOUT);
        element.clear();
        element.sendKeys(value);
    }

    protected void selectDropdownValueInContactTable(String title, String value, ContactType type) {
        WebDriverHelper.findElementByXpath(driver, getTableXpath(type) + String.format(TABLE_DROPDOWN_XPATH, title),
                ELEMENT_APPEAR_TIMEOUT).click();
        WebDriverHelper.findElementByXpath(driver, getTableXpath(type) + String.format(TABLE_DROPDOWN_XPATH, title) + String.format(DROPDOWN_OPTION_XPATH, value),
                TIMEOUT).click();
    }

    public InfoPage setFirstName(String value, ContactType type) {
        setInputValueInContactTable("First Name", value, type);
        return this;
    }

    public InfoPage setLastName(String value, ContactType type) {
        setInputValueInContactTable("Last Name", value, type);
        return this;
    }

    public InfoPage setAddress(String value, ContactType type) {
        setTextAreaValueInContactTable("Address", value, type);
        return this;
    }

    public InfoPage setCity(String value, ContactType type) {
        setInputValueInContactTable("City", value, type);
        return this;
    }

    public InfoPage setRegion(String value, ContactType type) {
        setInputValueInContactTable("State/Province", value, type);
        return this;
    }

    public InfoPage setPostalCode(String value, ContactType type) {
        setInputValueInContactTable("Postal Code", value, type);
        return this;
    }

    public InfoPage setPhone(String value) {
        setInputValueInContactTable("Phone", value, ContactType.BILLING_CONTACT);
        return this;
    }

    public InfoPage setCountry(String value, ContactType type) {
        selectDropdownValueInContactTable("Country", value, type);
        return this;
    }

    public InfoPage setEmail(String value) {
        WebDriverHelper.waitForElementVisibility(driver, emailInput, TIMEOUT);
        emailInput.clear();
        emailInput.sendKeys(value);
        return this;
    }

    public FinalPage clickPurchase() {
        WebDriverHelper.waitForElementVisibility(driver, purchaseButton, PAGE_LOADING_TIMEOUT);
        purchaseButton.click();
        FinalPage finalPage = new FinalPage(driver);
        finalPage.waitForPage();
        return finalPage;
    }

}
