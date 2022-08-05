package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Driver;
import com.qa.ims.utils.DBUtils;

public class DriverDAO implements Dao<Driver> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Driver modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("driver_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		return new Driver(id, firstName, surname);
	}

	/**
	 * Reads all drivers from the database
	 * 
	 * @return A list of drivers
	 */
	@Override
	public List<Driver> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM drivers");) {
			List<Driver> drivers = new ArrayList<>();
			while (resultSet.next()) {
				drivers.add(modelFromResultSet(resultSet));
			}
			return drivers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Driver readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM drivers ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a driver in the database
	 * 
	 * @param driver - takes in a driver object. id will be ignored
	 */
	@Override
	public Driver create(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO drivers(first_name, surname) VALUES (?, ?)");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Driver read(Long driver_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers WHERE id = ?");) {
			statement.setLong(1, driver_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a driver in the database
	 * 
	 * @param driver - takes in a driver object, the id field will be used to
	 *                 update that driver in the database
	 * @return
	 */
	@Override
	public Driver update(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE drivers SET first_name = ?, surname = ? WHERE id = ?");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.setLong(3, driver.getDriverId());
			statement.executeUpdate();
			return read(driver.getDriverId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a driver in the database
	 * 
	 * @param id - id of the driver
	 */
	@Override
	public int delete(long driver_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM drivers WHERE id = ?");) {
			statement.setLong(1, driver_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
