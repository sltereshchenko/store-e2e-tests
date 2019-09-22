package opm.contact;

public abstract class AbstractContact implements IContact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private ContactType contactType;

    public AbstractContact() {

    }

    public AbstractContact(ContactType type, IContact contact) {
        this.setContactType(type);
        this.setFirstName(contact.getFirstName());
        this.setLastName(contact.getLastName());
        this.setAddress(contact.getAddress());
        this.setCity(contact.getCity());
        this.setRegion(contact.getRegion());
        this.setCountry(contact.getCountry());
        this.setPostalCode(contact.getPostalCode());
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ContactType getContactType() {
        return this.contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

}
