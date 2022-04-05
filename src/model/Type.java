package model;

import java.time.LocalDateTime;
/**Create parameters for type. */
public class Type {
    String type;
    int count;
    LocalDateTime start;

    public Type(String type, int count, LocalDateTime start) {
        this.type = type;
        this.count = count;
        this.start = start;
    }
    /**Get type name.
     * @return Type Name. */
    public String getType() {
        return type;
    }
    /**Set type name. */
    public void setType(String type) {
        this.type = type;
    }
    /**Get type Count.
     * @return type count.*/
    public int getCount() {
        return count;
    }
    /**Set count. */
    public void setCount(int count) {
        this.count = count;
    }
    /**Get start time.
     * @return Start Time. */
    public LocalDateTime getStart() {
        return start;
    }
    /**Set Start Time. */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**Set  readable Type. */
    public String toString(){
        return("" + type);
    }
}
