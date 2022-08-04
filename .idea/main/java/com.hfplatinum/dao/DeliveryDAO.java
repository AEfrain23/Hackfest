package com.hfplatinum.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO implements DAO<Delivery> {

    @Override
    public Delivery modelFromResult(ResultSet result) {
        try {
            int orderId = result.getInt("order_id");
            Date date = result.getDate("delivery_date");
            String address = result.getString("delivery_address");
            String zone = result.getString("delivery_zone");
            int driverId = result.getInt("driver_id");
            String status = result.getString("delivery_status");
            return new Delivery(orderId, date, address, zone, driverId, status);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Delivery read(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM deliveries WHERE delivery_id = ?");) {
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


    public List<Delivery> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM deliveries;");) {
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Delivery> list = new ArrayList<Delivery>();

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

    public List<Delivery> readAllByZone(String zone) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM deliveries WHERE delivery_zone = ?;");) {
            statement.setString(1, zone);
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Delivery> list = new ArrayList<Delivery>();

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


    public Delivery readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM deliveries ORDER BY delivery_id DESC LIMIT 1;");) {
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResult(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Delivery create(Delivery d) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO deliveries(order_id, delivery_date, deliver_address, delivery_zone, delivery_status, driver_id) VALUES (?, ?, ?, ?, ?, ?);");) {
            statement.setInt(1, d.getOrderID());
            statement.setDate(2, d.getDeliveryDate());
            statement.setString(3, d.getDeliveryAddress());
            statement.setString(4,  d.getDeliveryZone());
            statement.setString(5,  d.getDeliveryStatus());
            statement.setInt(6, d.getDriverID());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Delivery update(Delivery d) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE deliveries SET order_id = ?, delivery_date = ?, deliver_address = ?, delivery_zone = ?, delivery_status = ?, driver_id = ? WHERE delivery_id = ?;");) {
            statement.setInt(1, d.getOrderID());
            statement.setDate(2, d.getDeliveryDate());
            statement.setString(3, d.getDeliveryAddress());
            statement.setString(4,  d.getDeliveryZone());
            statement.setString(5,  d.getDeliveryStatus());
            statement.setInt(7,  d.getDeliveryID());
            statement.setInt(6, d.getDriverID());
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
                     .prepareStatement("DELETE FROM deliveries WHERE delivery_id = ?");) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }


}

