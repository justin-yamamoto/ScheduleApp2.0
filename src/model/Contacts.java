package model;

/**Create a Contact objext. */
public class Contacts {

    int contactId;
    String contactName;
    String email;

    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
    /**Gets contact ID.
     * @return Contact ID. */
    public int getContactId() {
        return contactId;
    }
    /**Set Contact ID. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**Gets Contact Name.
     * @return Contact Name. */
    public String getContactName() {
        return contactName;
    }
    /**Sets Contact Name. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**Gets Email.
     * @return Email. */
    public String getEmail() {
        return email;
    }
    /**Sets Email. */
    public void setEmail(String email) {
        this.email = email;
    }

    /**Set Combo Box to  readable Contacts. */
    public String toString(){
        return(contactId + ": " + contactName);
    }
}
