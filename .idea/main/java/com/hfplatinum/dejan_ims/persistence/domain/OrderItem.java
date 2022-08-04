package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderItem {

    private Map<Long,Integer> itemIdQuantities;
    private Long orderID;
    private Long itemID;
    private Integer quantity;

    private double value;


    public OrderItem(Long orderID, Long itemID){
        this.orderID = orderID;
        this.itemID = itemID;
    }
    public OrderItem(Long orderID, Long itemID, Integer quantity){
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public OrderItem(Long orderID, Long itemID, Integer quantity, double value){
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.value = value;
    }

    public OrderItem(Long orderID, Long itemID, Double subtotal){
        this.orderID = orderID;
        this.itemID = itemID;
        this.value = subtotal;
    }


    public Map<Long, Integer> getItemIdQuantities() {
        return itemIdQuantities;
    }

    public void setItemIdQuantities(Map<Long, Integer> itemIdQuantities) {
        this.itemIdQuantities = itemIdQuantities;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Order ID: " + this.orderID +
            "| Item ID: " + this.itemID + "| Quantity: " + this.quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
        result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (getOrderID() == null) {
            if (other.getOrderID() != null)
                return false;
        } else if (!getOrderID().equals(other.getOrderID()))
            return false;
        if (getItemID() == null) {
            if (other.getItemID() != null)
                return false;
        } else if (!getItemID().equals(other.getItemID()))
            return false;
        if (getQuantity() == null) {
            if (other.getQuantity() != null)
                return false;
        } else if (!getQuantity().equals(other.getQuantity()))
            return false;
        return true;
    }

}
