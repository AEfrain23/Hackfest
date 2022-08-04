package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import com.qa.ims.utils.DBUtils;

import com.qa.ims.persistence.domain.Delivery;

public class DeliveryDAO implements Dao<Delivery> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Delivery> readAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Delivery read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Delivery create(Delivery t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Delivery update(Delivery t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Delivery modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
