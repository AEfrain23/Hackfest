package com.qa.newguinea.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.qa.newguinea.main.persistance.Driver;
import com.qa.newguinea.utils.DBUtils;

public class DriverDAO implements DAO<Driver> {

    @Override
    public Driver modelFromResult(ResultSet result) {
        try {
            int driverId = result.getInt("driver_id");
            String name = result.getString("driver_name");
            String zone = result.getString("driver_zone");
            int userId = result.getInt("user_id");
            return new Driver(driverId, name, userId, zone);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Driver read(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers WHERE driver_id = ?");) {
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

	/*
	@Override
	public List<Driver> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

    public Driver readUserId(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers WHERE user_id = ?");) {
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

    public Driver readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers ORDER BY driver_id DESC LIMIT 1;");) {
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
    public Driver create(Driver d) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO drivers(driver_name, driver_zone, user_id) VALUES (?, ?, ?)");) {
            statement.setString(1, d.getDriverName());
            statement.setString(2, d.getDriverZone());
            statement.setInt(3, d.getUserID());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Driver update(Driver d) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE drivers SET driver_name = ?, driver_zone = ?,  user_id = ?, WHERE driver_id = ?");) {
            statement.setString(1, d.getDriverName());
            statement.setString(2, d.getDriverZone());
            statement.setInt(3, d.getUserID());
            statement.setInt(4, d.getDriverID());
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
                     .prepareStatement("DELETE FROM drivers WHERE driver_id = ?");) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }



}
