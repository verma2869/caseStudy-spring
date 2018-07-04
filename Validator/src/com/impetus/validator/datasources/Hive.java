package com.impetus.validator.datasources;

import java.util.List;

import com.impetus.validator.connection.manager.ConnectionManager;
import com.impetus.validator.exception.ValidationException;

public class Hive extends DataSource{

	ConnectionManager connectionManager;

	public Hive(String user, String password, String dbName) {
		connectionManager = new ConnectionManager(user, password, dbName);
		connectionManager.url = "jdbc:hive://localhost:10000/";
	}

	@Override
	public List<String> getDataByTable(String table) throws ValidationException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDataForGivenColumns(String table, String[] columns, String conditions)
			throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSampleData(String table, String[] columns, int limit) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount(String table, String condition) throws ValidationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getColNames(String table) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getColDataType(String table) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColCount(String table) throws ValidationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getDataFromSQL(String sql) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}
}
