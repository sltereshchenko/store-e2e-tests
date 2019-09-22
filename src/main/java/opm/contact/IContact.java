package opm.contact;

public interface IContact {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    String getRegion();

    void setRegion(String region);

    String getCountry();

    void setCountry(String country);

    String getPostalCode();

    void setPostalCode(String postalCode);

    ContactType getContactType();

    void setContactType(ContactType contactType);
}