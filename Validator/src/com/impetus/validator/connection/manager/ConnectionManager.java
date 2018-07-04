package com.impetus.validator.connection.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.impetus.validator.exception.ValidationException;

public class ConnectionManager {
	public String url = "jdbc:mysql://localhost:3306/";
	public String user;
	public String password;
	public String dbName;
	public Connection conn;
	private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

	public ConnectionManager(String user, String password, String dbName) {
		this.user = user;
		this.password = password;
		this.dbName = dbName;
	}

	private Connection getConnection() throws ValidationException {

		if (validateParams(url, user, dbName)) {
			try {
				conn = DriverManager.getConnection(url + dbName + "?useSSL=false", user, password);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
				throw new ValidationException(e.getMessage(), e);
			}
		} else {
			logger.log(Level.SEVERE, "Invalid Parameterss");
			throw new ValidationException("Invalid Parameters");
		}
		return conn;
	}

	private static boolean validateParams(String url, String user, String dbName) {
		return !(url.isEmpty() || user.isEmpty() || dbName.isEmpty());
	}

	private static void closeConnection(Connection conn, ResultSet resultSet, Statement stmt)
			throws ValidationException {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		}
	}

	private static void closeConnection(Connection conn) throws ValidationException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		}
	}

	public Map<Integer, String> getData(String sql) throws ValidationException {

		int colCount;
		int rowCounter = 0;
		Map<Integer, String> data = new HashMap<>();
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSetMetaData metadata;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			metadata = resultSet.getMetaData();
			colCount = metadata.getColumnCount();
			while (resultSet.next()) {
				StringBuilder row = new StringBuilder();
				for (int i = 1; i <= colCount; i++) {
					if (i == 1) {
						row.append(resultSet.getString(i));
					} else {
						row.append(";" + resultSet.getString(i));
					}
				}
				data.put(rowCounter, row.toString());
				rowCounter++;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		} finally {
			closeConnection(conn, resultSet, stmt);
		}
		return data;
	}

	public ResultSetMetaData getMetaData(String sql) throws ValidationException {

		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSetMetaData metadata;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			metadata = resultSet.getMetaData();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage());
		}
		return metadata;
	}

	public List<String> getColumnNames(String sql) throws ValidationException {

		int colCount = 0;
		List<String> colNameList = new ArrayList<>();
		ResultSetMetaData metadata;

		try {
			metadata = getMetaData(sql);
			colCount = metadata.getColumnCount();
			for (int i = 0; i < colCount; i++) {
				colNameList.add(metadata.getColumnName(i + 1));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return colNameList;
	}

	public List<String> getColumnDataType(String sql) throws ValidationException {

		int colCount = 0;
		List<String> colDataTypeList = new ArrayList<>();
		ResultSetMetaData metadata;

		try {
			metadata = getMetaData(sql);
			colCount = metadata.getColumnCount();
			for (int i = 0; i < colCount; i++) {
				colDataTypeList.add(metadata.getColumnTypeName(i + 1));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return colDataTypeList;
	}
}
