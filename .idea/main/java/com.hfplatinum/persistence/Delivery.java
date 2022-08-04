package com.hfplatinum.controllers;

import java.sql.Date;
import java.util.Objects;

public class Delivery {
    private int deliveryID;
    private int orderID;
    private Date deliveryDate;
    private String deliveryAddress;
    private String deliveryZone;
    private int driverID;
    private String deliveryStatus;

    @Override
    public String toString() {
        return "Delivery [deliveryID=" + deliveryID + ", orderID=" + orderID + ", deliveryDate=" + deliveryDate
                + ", deliveryAddress=" + deliveryAddress + ", deliveryZone=" + deliveryZone + ", driverID=" + driverID
                + ", deliveryStatus=" + deliveryStatus + "]";
    }

    public Delivery(int deliveryID, int orderID, Date deliveryDate, String deliveryAddress, String deliveryZone,
                    int driverID, String deliveryStatus) {
        super();
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryZone = deliveryZone;
        this.driverID = driverID;
        this.deliveryStatus = deliveryStatus;
    }

    public Delivery(int orderID, Date deliveryDate, String deliveryAddress, String deliveryZone,
                    int driverID, String deliveryStatus) {
        super();
        this.orderID = orderID;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryZone = deliveryZone;
        this.driverID = driverID;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryZone() {
        return deliveryZone;
    }

    public void setDeliveryZone(String deliveryZone) {
        this.deliveryZone = deliveryZone;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryAddress, deliveryDate, deliveryID, deliveryStatus, deliveryZone, driverID, orderID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Delivery other = (Delivery) obj;
        return Objects.equals(deliveryAddress, other.deliveryAddress)
                && Objects.equals(deliveryDate, other.deliveryDate) && deliveryID == other.deliveryID
                && Objects.equals(deliveryStatus, other.deliveryStatus)
                && Objects.equals(deliveryZone, other.deliveryZone) && driverID == other.driverID
                && orderID == other.orderID;
    }


}

