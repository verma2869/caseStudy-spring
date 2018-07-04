package com.impetus.validator.datasources;

import java.util.List;
import java.util.stream.Collectors;

import com.impetus.validator.connection.manager.ConnectionManager;
import com.impetus.validator.exception.ValidationException;

public class MySQL extends DataSource {

	ConnectionManager connectionManager;

	public MySQL(String user, String password, String dbName) {
		connectionManager = new ConnectionManager(user, password, dbName);
		connectionManager.url = "jdbc:mysql://localhost:3306/";
	}

	public List<String> getDataByTable(String table) throws ValidationException {

		String sql = "Select * from " + table;
		return connectionManager.getData(sql).values().stream().collect(Collectors.toList());
	}

	public List<String> getDataFromSQL(String sql) throws ValidationException{
		
			return connectionManager.getData(sql).values().stream().collect(Collectors.toList());
	
	}

	public List<String> getDataForGivenColumns(String table, String[] columns, String conditions)
			throws ValidationException {
		StringBuilder sql = new StringBuilder("SELECT ");
		int col = columns.length;
		for (int i = 0; i < col; i++) {
			if (columns[i] == "*") {
				sql.append("*");
			} else {
				if (i == col - 1 && columns[i] != "*") {
					sql.append("`" + columns[i] + "`");
				} else {
					sql.append("`" + columns[i] + "`, ");
				}
			}
		}
		if (conditions != "") {
			sql.append(" FROM " + table + " WHERE " + conditions + ";");
		} else {
			sql.append(" FROM " + table + ";");
		}
		return connectionManager.getData(sql.toString()).values().stream().collect(Collectors.toList());
	}

	public List<String> getSampleData(String table, String[] columns, int limit) throws ValidationException {
		StringBuilder sql = new StringBuilder("SELECT ");
		int col = columns.length;
		for (int i = 0; i < col; i++) {
			if (columns[i] == "*") {
				sql.append("*");
			} else {
				if (i == col - 1 && columns[i] != "*") {
					sql.append("`" + columns[i] + "`");
				} else {
					sql.append("`" + columns[i] + "`, ");
				}
			}
		}
		sql.append(" FROM " + table + " LIMIT " + limit + ";");
		return connectionManager.getData(sql.toString()).values().stream().collect(Collectors.toList());
	}

	public int getRowCount(String table, String condition) throws ValidationException {
		String sql;
		if (condition.isEmpty()) {
			sql = "SELECT * FROM " + table;
		} else {
			sql = "SELECT * FROM " + table + " WHERE " + condition;
		}
		return connectionManager.getData(sql).values().size();
	}

	public List<String> getColNames(String table) throws ValidationException {
		String sql = "SELECT * FROM " + table;
		return connectionManager.getColumnNames(sql);
	}

	public List<String> getColDataType(String table) throws ValidationException {
		String sql = "SELECT * FROM " + table;
		return connectionManager.getColumnDataType(sql);
	}

	public int getColCount(String table) throws ValidationException {
		return getColDataType(table).size();
	}

}
