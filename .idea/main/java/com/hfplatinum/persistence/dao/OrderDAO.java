package com.qa.newguinea.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qa.newguinea.main.persistance.Delivery;
import com.qa.newguinea.main.persistance.Order;
import com.qa.newguinea.utils.DBUtils;

public class OrderDAO implements DAO<Order> {

    @Override
    public Order read(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?;");) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResult(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResult(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1;");) {
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Order> list = new ArrayList<Order>();

                while(resultSet.next()) {
                    list.add(modelFromResult(resultSet));
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @Override
    public Order create(Order o) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(customer_id, order_date, order_address) VALUES (?, ?, ?);");) {
            statement.setInt(1, o.getCustomerid());
            statement.setDate(2, o.getOrderdate());
            statement.setString(3, o.getCustomeraddress());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Order update(Order o) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE orders SET customer_id = ?, order_date = ?, order_address = ? WHERE order_id = ?;");) {
            statement.setInt(1, o.getCustomerid());
            statement.setDate(2, o.getOrderdate());
            statement.setString(3, o.getCustomeraddress());
            statement.setInt(4, o.getOrderId());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int delete(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("DELETE FROM orders WHERE order_id = ?;");) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Order modelFromResult(ResultSet result) {
        try {
            int orderId = result.getInt("order_id");
            int customerId = result.getInt("customer_id");
            Date orderDate = result.getDate("order_date");
            String orderAddress = result.getString("order_address");
            return new Order(orderId, customerId, orderDate, orderAddress);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
