package com.qa.ims.persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.DBUtils;

public class OrderItemDAO {

    public static final Logger LOGGER = LogManager.getLogger();

    public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderID = resultSet.getLong("order_id");
        Long itemID = resultSet.getLong("item_id");
        Integer quantity  = resultSet.getInt("quantity");

        return new OrderItem(orderID, itemID, quantity);
    }

    public OrderItem calcRead(ResultSet resultSet) throws SQLException {
        Double subtotal = resultSet.getDouble("subtotal");
        Long itemID = resultSet.getLong("item_id");
        Long orderID  = resultSet.getLong("order_id");

        return new OrderItem(orderID, itemID, subtotal);
    }

    /**
     * Adds an item to the order_item table
     *
     * @param orderItem - takes in an order item object
     */
    public OrderItem addItem(OrderItem orderItem) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO order_item(order_id, item_id, quantity) VALUES (?, ?, ?)");
        ) {
            statement.setLong(1, orderItem.getOrderID());
            statement.setLong(2, orderItem.getItemID());
            statement.setInt(3, orderItem.getQuantity());
            statement.executeUpdate();
            LOGGER.info("Item successfully added.");
            return orderItem;
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
            LOGGER.info("Attempt Failed.");
        }
        return null;
    }

    public List<OrderItem> read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM order_item WHERE order_id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                List<OrderItem> orderItems = new ArrayList<>();
                while (resultSet.next()) {
                    orderItems.add(modelFromResultSet(resultSet));
                }
                return orderItems;
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<OrderItem> readForCalculation(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT oi.order_id, oi.item_id, oi.quantity * i.item_value AS subtotal FROM order_item oi , item i WHERE order_id = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<OrderItem> orderItems = new ArrayList<>();
                while (resultSet.next()) {
                    orderItems.add(calcRead(resultSet));
                }
                return orderItems;
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    /**
     * Deletes an item in the database
     *
     * @param orderID - id of the order
     * @param itemID - id of the item
     */

    public int deleteItem(Long orderID, Long itemID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM order_item WHERE order_id = ? AND item_id = ?");
        ) {
            statement.setLong(1, orderID);
            statement.setLong(2, itemID);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}


