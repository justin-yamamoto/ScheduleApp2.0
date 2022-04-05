package model;
/**Set parameters for firstleveldivision. */
public class FirstLevelDivisions {

    int divisionId;
    String division;
    int countryId;

    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }
    /**Gets division ID.
     * @return division ID. */
    public int getDivisionId() {
        return divisionId;
    }
    /**Sets division ID. */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**Gets division name.
     * @return Division Name. */
    public String getDivision() {
        return division;
    }
    /**Set division name. */
    public void setDivision(String division) {
        this.division = division;
    }
    /**Gets country ID.
     * @return Country ID. */
    public int getCountryId() {
        return countryId;
    }
    /**Set country ID. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**Set Combo Box to  readable Division. */
    public String toString(){
        return(division);
    }
}
