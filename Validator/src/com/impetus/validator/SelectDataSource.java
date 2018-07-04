package com.impetus.validator;

import com.impetus.validator.datasources.DataSource;
import com.impetus.validator.datasources.Hive;
import com.impetus.validator.datasources.MySQL;
import com.impetus.validator.exception.ValidationException;

public class SelectDataSource {

	public static DataSource setSource(String sourceType, String sourceUser, String sourcePassword, String sourceDb)
			throws ValidationException {
		if (sourceType.equalsIgnoreCase("Hive")) {
			return new Hive(sourceUser, sourcePassword, sourceDb);
		} else if (sourceType.equalsIgnoreCase("rdbms")) {
			return new MySQL(sourceUser, sourcePassword, sourceDb);
		} else {
			throw new ValidationException("Invalid source type");
		}
	}

	public static DataSource setTarget(String targetType, String targetUser, String targetPassword, String targetDb)
			throws ValidationException {
		if (targetType.equalsIgnoreCase("Hive")) {
			return new Hive(targetUser, targetPassword, targetDb);
		} else if (targetType.equalsIgnoreCase("rdbms")) {
			return new MySQL(targetUser, targetPassword, targetDb);
		} else {
			throw new ValidationException("Invalid source type");
		}
	}
}
