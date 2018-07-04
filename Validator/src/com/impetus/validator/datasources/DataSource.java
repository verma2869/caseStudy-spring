package com.impetus.validator.datasources;

import java.util.List;

import com.impetus.validator.exception.ValidationException;

public abstract class DataSource {

	private static String url;

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DataSource.url = url;
	}

	public abstract List<String> getDataByTable(String table) throws ValidationException;

	public abstract List<String> getDataForGivenColumns(String table, String[] columns, String conditions)
			throws ValidationException;

	public abstract List<String> getDataFromSQL(String sql) throws ValidationException;
	
	public abstract List<String> getSampleData(String table, String[] columns, int limit) throws ValidationException;

	public abstract int getRowCount(String table, String condition) throws ValidationException;

	public abstract List<String> getColNames(String table) throws ValidationException;

	public abstract List<String> getColDataType(String table) throws ValidationException;

	public abstract int getColCount(String table) throws ValidationException;
	
}
