package com.hfplatinum.persistence;

import java.util.Objects;

public class Customers {
    private int customerID;
    private String custName;
    private int userID;
    private String custAdress;

    public Customers(int customerID, String custName, int userID, String custAdress) {
        super();
        this.customerID = customerID;
        this.custName = custName;
        this.userID = userID;
        this.custAdress = custAdress;
    }


    public Customers(String custName, int userID, String custAdress) {
        super();
        this.custName = custName;
        this.userID = userID;
        this.custAdress = custAdress;
    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCustAdress() {
        return custAdress;
    }

    public void setCustAdress(String custAdress) {
        this.custAdress = custAdress;
    }

    @Override
    public String toString() {
        return "Customers [customerID=" + customerID + ", custName=" + custName + ", userID=" + userID + ", custAdress="
                + custAdress + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(custAdress, custName, customerID, userID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customers other = (Customers) obj;
        return Objects.equals(custAdress, other.custAdress) && Objects.equals(custName, other.custName)
                && customerID == other.customerID && userID == other.userID;
    }


}

