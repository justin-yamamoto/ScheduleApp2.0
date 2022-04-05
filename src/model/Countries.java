package model;
/**Create a Countries object. */
public class Countries {

    int countryId;
    String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }
    /**Gets country ID.
     * @return Country ID. */
    public int getCountryId() {
        return countryId;
    }
    /**Set Country ID. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**Gets Country name.
     * @return Country name.*/
    public String getCountry() {
        return country;
    }

    /**Set country name. */
    public void setCountry(String country) {
        this.country = country;
    }

    /**Set Combo Box to  readable Country. */
    public String toString(){
        return(country);
    }
}
