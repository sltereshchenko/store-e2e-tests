package opm.contact;

public class ShippingContact extends AbstractContact {
    public ShippingContact(IContact contact) {
        super(ContactType.SHIPPING_CONTACT, contact);
    }
}
