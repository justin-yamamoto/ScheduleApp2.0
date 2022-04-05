package model;

import java.time.LocalDateTime;

/**Create an appointment object. */
public class Appointments {

    int appointmentId;
    String title;
    String description;
    String location;
    String type;
    LocalDateTime start;
    LocalDateTime end;
    int customerId;
    int userId;
    int contactId;

    public Appointments(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
    /**Gets Appointment ID.
     * @return Appointment ID.*/
    public int getAppointmentId() {
        return appointmentId;
    }
    /**Set Appointment ID. */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**Gets title.
     * @return Title. */
    public String getTitle() {
        return title;
    }
    /**Set title.*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**Gets description.
     * @return Description. */
    public String getDescription() {
        return description;
    }
    /**Set description. */
    public void setDescription(String description) {
        this.description = description;
    }
    /**Gets locations.
     * @return Description. */
    public String getLocation() {
        return location;
    }
    /**Set location. */
    public void setLocation(String location) {
        this.location = location;
    }
    /**Gets type.
     * @return Type. */
    public String getType() {
        return type;
    }
    /**Set type. */
    public void setType(String type) {
        this.type = type;
    }
    /**Gets start time.
     * @return Start time. */
    public LocalDateTime getStart() {
        return start;
    }
    /**Set start time. */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**Gets end time.
     * @return End time.*/
    public LocalDateTime getEnd() {
        return end;
    }
    /**Set end time. */
    public void setEnd(LocalDateTime end) {
        this.end = end;
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
    /**Gets user ID.
     * @return User ID. */
    public int getUserId() {
        return userId;
    }
    /**Sets User ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**Gets contact ID.
     * @return Contact ID. */
    public int getContactId() {
        return contactId;
    }
    /**Sets contact ID. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**Set  readable Appointment. */
    public String toString(){
        return("" + start);
    }
}
