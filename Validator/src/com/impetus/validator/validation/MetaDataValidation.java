package com.impetus.validator.validation;

import java.util.List;

import com.impetus.validator.SelectDataSource;
import com.impetus.validator.datasources.DataSource;
import com.impetus.validator.exception.ValidationException;

public class MetaDataValidation {
	DataSource ds;

	public MetaDataValidation(String type, String user, String password, String dbName) throws ValidationException {
		ds = SelectDataSource.setTarget(type, user, password, dbName);
	}

	public int getColumnCount(String table) throws ValidationException {
		return ds.getColCount(table);
	}

	public List<String> getColDataType(String table) throws ValidationException {
		return ds.getColDataType(table);
	}

	public List<String> getColumnNames(String table) throws ValidationException {
		return ds.getColNames(table);
	}

	public String getColumnDetails(String table) throws ValidationException {
		StringBuilder column = new StringBuilder();
		List<String> columnDataTypeList = getColDataType(table);
		List<String> columnNameList = getColumnNames(table);
		int colCount = getColumnCount(table);

		for (int i = 0; i < colCount; i++) {
			column.append(String.format("%-51s %3s", columnNameList.get(i), columnDataTypeList.get(i)));
			column.append(System.getProperty("line.separator"));
		}
		return column.toString();
	}
}
