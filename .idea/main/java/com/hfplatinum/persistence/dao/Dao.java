package com.hfplatinum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	List<T> readAll();
	
	T read(Long id);

	T create(T t);

	T update(T t);

	int delete(Long id);

	T modelFromResultSet(ResultSet resultSet) throws SQLException;
}
