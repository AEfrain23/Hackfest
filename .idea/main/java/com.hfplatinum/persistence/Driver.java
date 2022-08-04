package com.hfplatinum.persistence;
package com.qa.newguinea.main.persistance;

import java.util.Objects;

public class Driver {
    private int driverID;
    private String driverName;
    private int userID;
    private String driverZone;

    public Driver(int driverID, String driverName, int userID, String driverZone) {
        super();
        this.driverID = driverID;
        this.driverName = driverName;
        this.userID = userID;
        this.driverZone = driverZone;
    }

    public Driver(String driverName, int userID, String driverZone) {
        super();
        this.driverName = driverName;
        this.userID = userID;
        this.driverZone = driverZone;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDriverZone() {
        return driverZone;
    }

    public void setDriverZone(String driverZone) {
        this.driverZone = driverZone;
    }

    @Override
    public String toString() {
        return "Driver [driverID=" + driverID + ", driverName=" + driverName + ", userID=" + userID + ", driverZone="
                + driverZone + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverID, driverName, driverZone, userID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Driver other = (Driver) obj;
        return driverID == other.driverID && Objects.equals(driverName, other.driverName)
                && Objects.equals(driverZone, other.driverZone) && userID == other.userID;
    }


}
