package com.impetus.validator.validation;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.impetus.validator.SelectDataSource;
import com.impetus.validator.datasources.DataSource;
import com.impetus.validator.exception.ValidationException;

public class DataValidation {

	DataSource ds;
	private static final Logger logger = Logger.getLogger(DataValidation.class.getName());

	public DataValidation(String type, String user, String password, String dbName) throws ValidationException {
		ds = SelectDataSource.setSource(type, user, password, dbName);
	}

	public String printTab(int num) {
		StringBuilder tab = new StringBuilder();
		for (int i = 0; i < num; i++) {
			tab.append("\t");
		}
		return tab.toString();
	}

	public List<String> getSampleData(String table, int limit) throws ValidationException {
		String[] column = { "*" };
		return ds.getSampleData(table, column, limit);
	}

	public String getHashCode(String data) throws ValidationException {
		MessageDigest digest;
		byte[] hash = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			hash = digest.digest(data.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(), e);
		}
		return Arrays.toString(hash);
	}

	public int getRowCount(String table, String condition) throws ValidationException {
		return ds.getRowCount(table, condition);
	}

	public List<String> getDataFromSQL(String sql) throws ValidationException {
		return ds.getDataFromSQL(sql);

	}

	public List<String> getDataByTableName(String table) throws ValidationException {
		return ds.getDataByTable(table);
	}

	public List<String> convertToMD5(List<String> list) {
		return list.stream().peek(s -> {
			try {
				getHashCode(s);
			} catch (ValidationException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}).collect(Collectors.toList());
	}

	public String arrayToSring(String[] arr) {
		return Arrays.toString(arr);
	}

}
