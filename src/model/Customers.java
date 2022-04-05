package model;

/**Creates a Customers object. */
public class Customers {

    int customerId;
    String customerName;
    String address;
    String postalCode;
    String phone;
    String country;
    String division;

    public Customers(int customerId, String customerName, String address, String postalCode, String phone, String country, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.division = division;
    }

    /**Gets customer ID.
     * @return customer ID. */
    public int getCustomerId() {
        return customerId;
    }
    /**Sets customer ID. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**Gets customer name.
     * @return Customer Name. */
    public String getCustomerName() {
        return customerName;
    }
    /**Set customer name. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**Get address.
     * @return address. */
    public String getAddress() {
        return address;
    }
    /**Set address. */
    public void setAddress(String address) {
        this.address = address;
    }
    /**Gets postal code.
     * @return postal code. */
    public String getPostalCode() {
        return postalCode;
    }
    /**Set postal code. */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**Get phone number.
     * @return phone number. */
    public String getPhone() {
        return phone;
    }
    /**Sets phone numbers. */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**Gets Country.
     * @return Country. */
    public String getCountry() {
        return country;
    }
    /**Sets country. */
    public void setCountry(String country) {
        this.country = country;
    }
    /**Gets Division.
     * @return  Division. */
    public String getDivision() {
        return division;
    }
    /**Sets Division. */
    public void setDivision(String division) {
        this.division = division;
    }
    /**Set Combo Box to  readable Contacts. */
    public String toString(){
        return(customerId + ": " + customerName);
    }
}
