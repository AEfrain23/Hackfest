package com.qa.newguinea.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qa.newguinea.main.persistance.Manager;
import com.qa.newguinea.utils.DBUtils;

public class ManagerDAO implements DAO<Manager> {
    @Override
    public Manager modelFromResult(ResultSet result) {
        try {
            int managerId = result.getInt("manager_id");
            String name = result.getString("manager_name");
            int userId = result.getInt("user_id");
            return new Manager(managerId, name, userId);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Manager read(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers WHERE manager_id = ?");) {
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

    public Manager readByUserId(int id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers WHERE user_id = ?");) {
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

    public Manager readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers ORDER BY manager_id DESC LIMIT 1;");) {
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
    public Manager create(Manager m) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO managers(manager_name, user_id) VALUES (?, ?);");) {
            statement.setString(1, m.getManagername());
            statement.setInt(2, m.getUserId());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Manager update(Manager m) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE managers SET manager_name = ?, user_id = ? WHERE driver_id = ?");) {
            statement.setString(1, m.getManagername());
            statement.setInt(2, m.getUserId());
            statement.setInt(3, m.getManagerid());
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
                     .prepareStatement("DELETE FROM managers WHERE manager_id = ?");) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }

}

