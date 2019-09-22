package opm.contact;

public class BillingContact extends AbstractContact {
    private final String phone;

    public BillingContact(IContact contact, String phone) {
        super(ContactType.BILLING_CONTACT, contact);
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }
}
