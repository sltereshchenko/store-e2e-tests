package opm.contact;

public class ContactBuilder {
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String city = "";
    private String region = "";
    private String country = "";
    private String postalCode = "";
    private String phone = "";

    public static IContact getDummyContact(ContactType type) {
        return new ContactBuilder().firstName("Slava").lastName("Ter").address("2 Glenarden Rd").city("Toronto")
                .region("Ontario").country("Canada").postalCode("M6C").phone("506-607-1637").build(type);
    }

    public ContactBuilder firstName(String value) {
        this.firstName = value;
        return this;
    }

    public ContactBuilder lastName(String value) {
        this.lastName = value;
        return this;
    }

    public ContactBuilder address(String value) {
        this.address = value;
        return this;
    }

    public ContactBuilder city(String value) {
        this.city = value;
        return this;
    }

    public ContactBuilder region(String value) {
        this.region = value;
        return this;
    }

    public ContactBuilder country(String value) {
        this.country = value;
        return this;
    }

    public ContactBuilder postalCode(String value) {
        this.postalCode = value;
        return this;
    }

    public ContactBuilder phone(String value) {
        this.phone = value;
        return this;
    }

    public IContact build(ContactType type) {
        Contact contact = new Contact();
        contact.setFirstName(this.firstName);
        contact.setLastName(this.lastName);
        contact.setAddress(this.address);
        contact.setCity(this.city);
        contact.setRegion(this.region);
        contact.setCountry(this.country);
        contact.setPostalCode(this.postalCode);
        if (type == ContactType.BILLING_CONTACT)
            return new BillingContact(contact, this.phone);
        if (type == ContactType.SHIPPING_CONTACT)
            return new ShippingContact(contact);
        throw new IllegalArgumentException(String.format("%s ContactType is not implemented.", type.name()));

    }

}
