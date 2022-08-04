package com.hfplatinum.persistence.domain;
package com.qa.newguinea.main.persistance;

import java.sql.Date;
import java.util.Objects;

public class Order {

    private int orderId;
    private int customerid;
    private Date orderdate;
    private String customeraddress;

    public Order(int orderId, int customerid, Date orderdate, String customeraddress) {
        super();
        this.setOrderId(orderId);
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.customeraddress = customeraddress;
    }

    public Order(int customerid, Date orderdate, String customeraddress) {
        super();
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.customeraddress = customeraddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerid() {
        return customerid;
    }
    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    public Date getOrderdate() {
        return orderdate;
    }
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }
    public String getCustomeraddress() {
        return customeraddress;
    }
    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }
    @Override
    public String toString() {
        return "Order [customerid=" + customerid + ", orderdate=" + orderdate + ", customeraddress=" + customeraddress
                + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(customeraddress, customerid, orderdate);
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
        return Objects.equals(customeraddress, other.customeraddress) && customerid == other.customerid
                && Objects.equals(orderdate, other.orderdate);
    }
}

