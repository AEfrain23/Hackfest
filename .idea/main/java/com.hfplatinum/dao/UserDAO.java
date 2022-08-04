package com.qa.newguinea.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qa.newguinea.utils.DBUtils;

public class UserDAO {


    public int logIn(String username, String password) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM customers WHERE username = ? AND user_password = ?");) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return resultSet.getInt("user_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }
}

