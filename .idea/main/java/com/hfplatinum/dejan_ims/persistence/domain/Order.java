package com.qa.ims.persistence.domain;

import com.qa.ims.persistence.dao.OrderItemDAO;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private Long id;
    private Long customerID;
    private Date date;
    private Double value;
    private Map<Long, Integer> itemMap = new HashMap<>();
    private ArrayList<OrderItem> items = new ArrayList<>();


    public Order(Long customerID, Date date) {
        this.customerID = customerID;
        this.date = date;
    }

    public Order(Long id, Long customerID, Date date) {
        this.id = id;
        this.customerID = customerID;
        this.value = value;
        this.date = date;
    }

    public Order(Long id, Long customerID, Double value, Date date) {
        this.id = id;
        this.customerID = customerID;
        this.value = value;
        this.date = date;
    }

    public void addOrderItem(OrderItem oi){
        this.items.add(oi);
    }

    public void removeOrderItem(Long orderID, Long itemID) {
        for (OrderItem oi : items) {
            if (orderID.equals(oi.getOrderID()) && itemID.equals(oi.getItemID())){
                items.remove(oi);
            }
        }

    }



    public Double calculateValue(Long orderID){
        OrderItemDAO oid = new OrderItemDAO();
        List<OrderItem> ordaah;
        ordaah = oid.readForCalculation(orderID);
        Double total = 0.0;
        for (OrderItem oi : ordaah) {
           total +=  oi.getValue();
        }
        return total;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public Map<Long, Integer> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Long, Integer> items) {
        this.itemMap = itemMap;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Order ID: " + this.id +
                " | Customer ID: " + this.customerID +
                " | Value: " + df.format(this.value) +
                " | Order Date: " + this.date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
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
        Order other = (Order) obj;
        if (getCustomerID() == null) {
            if (other.getCustomerID() != null)
                return false;
        } else if (!getCustomerID().equals(other.getCustomerID()))
            return false;
        if (getID() == null) {
            if (other.getID() != null)
                return false;
        } else if (!getID().equals(other.getID()))
            return false;
        if (getValue() == null) {
            if (other.getValue() != null)
                return false;
        } else if (!getValue().equals(other.getValue()))
            return false;
        if (getDate() == null) {
            if (other.getDate() != null)
                return false;
        } else if (!getDate().equals(other.getDate()))
            return false;
        return true;
    }

}


