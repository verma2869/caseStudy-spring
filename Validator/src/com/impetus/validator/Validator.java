package com.impetus.validator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.impetus.validator.exception.ValidationException;
import com.impetus.validator.generate.report.GenerateReport;
import com.impetus.validator.validation.DataValidation;
import com.impetus.validator.validation.MetaDataValidation;

public class Validator {

	MetaDataValidation sourceMetadata;
	MetaDataValidation targetMetadata;
	DataValidation sourceData;
	DataValidation targetData;
	private static final String SEPARATOR = System.getProperty("line.separator");
	private static final String SOURCE = "Source Table: ";
	private static final String TARGET = "Target Table: ";
	private static final Logger logger = Logger.getLogger(Validator.class.getName());

	public Validator(String sourceType, String sourceUser, String sourcePassword, String sourceDb, String targetType,
			String targetUser, String targetPassword, String targetDb) throws ValidationException {

		try {
			sourceMetadata = new MetaDataValidation(sourceType, sourceUser, sourcePassword, sourceDb);
			targetMetadata = new MetaDataValidation(targetType, targetUser, targetPassword, targetDb);
			sourceData = new DataValidation(sourceType, sourceUser, sourcePassword, sourceDb);
			targetData = new DataValidation(targetType, targetUser, targetPassword, targetDb);
		} catch (ValidationException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new ValidationException(e.getMessage(),e);
		}
	}

	GenerateReport gr = new GenerateReport();

	public void printConnectionReport(String sourceType, String targetType, String sourceDb, String targetDb,
			String sourceUser, String targetUser, String sourceTable, String targetTable) {
		StringBuilder content = new StringBuilder();
		content.append(
				"...................................................................................................");
		content.append(SEPARATOR);
		content.append(String.format("Source Datasource: %-50s Destinaiton Datasource: %3s", sourceType, targetType));
		content.append(SEPARATOR);
		content.append(String.format("Source Database: %-52s Destinaiton Database: %3s", sourceDb, targetDb));
		content.append(SEPARATOR);
		content.append(String.format("Source User: %-56s Destinaiton user: %3s", sourceUser, targetUser));
		content.append(SEPARATOR);
		content.append(String.format("Source Table: %-55s Destinaiton Table: %3s", sourceTable, targetTable));
		content.append(SEPARATOR);

		gr.printAndWrite(content.toString());
	}

	public void compareColumnCount(String sourceTable, String destinationTable) throws ValidationException {

		int sourceColCount = sourceMetadata.getColumnCount(sourceTable);
		int destinationColCount = targetMetadata.getColumnCount(destinationTable);
		StringBuilder content = new StringBuilder();
		content.append(SEPARATOR);
		content.append("Metadata Validation...............................................");
		content.append(SEPARATOR);
		content.append("Validating Column Counts for source table and destination table...");
		if (sourceColCount > destinationColCount) {
			content.append(SEPARATOR);
			content.append("Source table has more columns. Details are given below");

		} else if (sourceColCount < destinationColCount) {
			content.append(SEPARATOR);
			content.append("Destination table has more columns. Details are given below");

		} else if (sourceColCount == destinationColCount) {
			content.append(SEPARATOR);
			content.append("Column count matches in both table");
		} else {
			content.append(SEPARATOR);
			content.append("Error while comparing columns");
			logger.log(Level.SEVERE, "Error while comparing columns");
		}
		content.append(SEPARATOR + SOURCE + SEPARATOR);
		content.append(sourceMetadata.getColumnDetails(sourceTable));
		content.append(SEPARATOR + TARGET + SEPARATOR);
		content.append(targetMetadata.getColumnDetails(destinationTable));
		gr.printAndWrite(content.toString());
	}

	public void compareDataType(String sourceTable, String targetTable) throws ValidationException {

		StringBuilder content = new StringBuilder();
		content.append("Validating Column Datatypes for source table and destination table...");
		List<String> sourceTableColumnType = sourceMetadata.getColDataType(sourceTable);
		List<String> destinationTableColumnType = targetMetadata.getColDataType(targetTable);
		List<String> sourceTableColumnName = sourceMetadata.getColumnNames(sourceTable);
		List<String> destinationTableColumnName = targetMetadata.getColumnNames(targetTable);

		int count = 0;
		int j = 0;
		int sourceColCount = sourceTableColumnName.size();
		int targetColCount = destinationTableColumnName.size();
		int colDiff = Math.abs(sourceColCount - targetColCount);

		content.append(SEPARATOR + "Detail of the columns which do not match:");
		content.append(String.format("%n%-51s %2s%n", SOURCE, TARGET));

		if (sourceColCount > targetColCount) {
			destinationTableColumnType.addAll(Collections.nCopies(colDiff, "-"));
			destinationTableColumnName.addAll(Collections.nCopies(colDiff, "-"));
		} else if (sourceColCount < targetColCount) {
			sourceTableColumnType.addAll(Collections.nCopies(colDiff, "-"));
			sourceTableColumnName.addAll(Collections.nCopies(colDiff, "-"));
		}

		for (int i = 0; i < sourceColCount || i < targetColCount; i++) {

			if (sourceTableColumnType.get(i) == destinationTableColumnType.get(i)
					&& (sourceTableColumnType.get(i) != "-" || destinationTableColumnType.get(i) != "-")) {
				count++;
			} else {
				content.append(String.format("%n%-20s %-30s %-20s %3s", sourceTableColumnName.get(i),
						sourceTableColumnType.get(i), destinationTableColumnName.get(i),
						destinationTableColumnType.get(i)));
				j++;
			}
		}
		if (count == targetColCount) {
			content.append(SEPARATOR + "All column data type matches in source table and destination tables");
		} else {
			content.append(String.format("%nNo of columns matches: %1s", count));
			content.append(String.format("%nNo of columns do not matches: %1s%n", j));
		}
		gr.printAndWrite(content.toString());
	}

	public void validateSamples(String sourceTable, String targetTable, int limit) throws ValidationException {

		StringBuilder content = new StringBuilder();
		content.append("Data Validation........................................");
		content.append(SEPARATOR);
		content.append("Validating samples from source and target tables....");
		content.append(SEPARATOR + "Sample Size: " + limit);
		content.append(SEPARATOR + "Details of Samples which do not match");
		List<String> sourceColName = sourceMetadata.getColumnNames(sourceTable);
		List<String> targetColName = targetMetadata.getColumnNames(targetTable);

		List<String> sourceSampleData = sourceData.getSampleData(sourceTable, limit);
		List<String> targetSampleData = targetData.getSampleData(targetTable, limit);

		int sourceColumnCount = sourceColName.size();
		int destinationColumnCount = targetColName.size();
		int count = limit;
		content.append(String.format("%n%-50s %1s%n", SOURCE, TARGET));
		StringBuilder colNames = new StringBuilder();
		for (int i = 0; i < sourceColumnCount; i++) {
			colNames.append(sourceColName.get(i) + "\t");
		}
		colNames.append("\t\t");
		for (int i = 0; i < destinationColumnCount; i++) {
			colNames.append(targetColName.get(i) + "\t");
		}
		content.append("" + colNames);
		content.append(SEPARATOR);
		if (sourceColumnCount == destinationColumnCount) {
			for (int i = 0; i < limit; i++) {
				if (sourceSampleData.get(i).equalsIgnoreCase(targetSampleData.get(i))) {
					count--;
				} else {
					String sRow = sourceData.arrayToSring(sourceSampleData.get(i).split(";"));
					String tRow = targetData.arrayToSring(targetSampleData.get(i).split(";"));
					content.append(sRow + "\t\t" + tRow);
					content.append(SEPARATOR);
				}
			}
		} else {
			content.append("Column count does not match for source table '" + sourceTable + "'" + " and target table '"
					+ targetTable + "'");
		}

		if (count == 0) {
			content.append(SEPARATOR + "All Samples matched Successfully");
		}
		gr.printAndWrite(content.toString());
	}

	public void validateRowbyRow(String sourceTable, String destinationTable) throws ValidationException {

		StringBuilder content = new StringBuilder();
		content.append(SEPARATOR);
		content.append("Validating Row by Row using MD5 from source table and target tables...");

		List<String> sourceList = sourceData.getDataByTableName(sourceTable);
		List<String> destinationList = targetData.getDataByTableName(destinationTable);

		List<String> sourceHash = sourceData.convertToMD5(sourceList);
		List<String> targetHash = targetData.convertToMD5(destinationList);
		int rowCounter = sourceList.size();
		if (sourceHash.size() == targetHash.size()) {
			for (int i = 0; i < sourceHash.size(); i++) {
				if (sourceHash.get(i).equalsIgnoreCase(targetHash.get(i))) {
					rowCounter--;
				} else {
					content.append(SEPARATOR);
					content.append("Source Row not matched: " + sourceData.arrayToSring(sourceList.get(i).split(";")));
					content.append(SEPARATOR);
					content.append(
							"Target Row not matched: " + sourceData.arrayToSring(destinationList.get(i).split(";")));
				}
			}
		} else if (sourceHash.size() > targetHash.size()) {
			sourceList.removeAll(destinationList);
			content.append(SEPARATOR + "Extra Source Records...");
			Iterator<String> itr = sourceList.iterator();
			int i = 10;
			String row = "";
			while (itr.hasNext()) {
				row = itr.next();
				i--;
				if (i == 0) {
					content.append(SEPARATOR);
					content.append("and " + Math.abs(sourceHash.size() - 9) + " more...");
					break;
				} else {
					content.append(SEPARATOR);
					content.append(row);
				}
			}
		} else if (sourceHash.size() < targetHash.size()) {
			destinationList.removeAll(sourceList);
			content.append(SEPARATOR + "Extra Target Records...");
			Iterator<String> itr = destinationList.iterator();
			int i = 10;
			String row = "";
			while (itr.hasNext()) {
				i--;
				row = itr.next();
				if (i == 0) {
					content.append(SEPARATOR + "and " + Math.abs(targetHash.size() - 9) + " more...");
					break;
				} else {
					content.append(SEPARATOR);
					content.append(row);
				}
			}
		}
		if (rowCounter == 0) {
			content.append(SEPARATOR);
			content.append("All rows matched");
		}
		gr.printAndWrite(content.toString());
	}

	public void customQueryOutputValidation(String sourceSQL, String targetSQL) throws ValidationException {

		StringBuilder content = new StringBuilder();
		content.append(SEPARATOR + "Validating output from custom query for source table and target tables...");
		List<String> sourceList = sourceData.getDataFromSQL(sourceSQL);
		List<String> destinationList = targetData.getDataFromSQL(targetSQL);
		int sourceListCount = sourceList.size();
		int destListCount = destinationList.size();
		int count = sourceListCount;
		if (sourceListCount == destListCount) {
			for (int i = 0; i < destListCount; i++) {
				if (sourceList.get(i).equalsIgnoreCase(destinationList.get(i))) {
					count--;
				} else {
					content.append(SEPARATOR);
					content.append("Source Row not matched: " + sourceList.get(i));
					content.append(SEPARATOR);
					content.append("Destination Row not matched: " + destinationList.get(i));
				}
			}
		} else {
			content.append(SEPARATOR);
			content.append("Number of rows are different in source and target tables");
		}
		if (count == 0) {
			content.append(SEPARATOR);
			content.append("All rows matched in source and target tables");
		}
		gr.printAndWrite(content.toString());
	}

	public void customQueryRowCountValidation(String sourceSQL, String targetSQL) throws ValidationException {

		StringBuilder content = new StringBuilder();
		content.append(SEPARATOR + "Validating row count from custom query for source table and target tables...");

		int sourceListCount = sourceData.getDataFromSQL(sourceSQL).size();
		int destListCount = targetData.getDataFromSQL(targetSQL).size();

		if (sourceListCount == destListCount) {
			content.append(SEPARATOR);
			content.append("Number of rows matched.");
		} else {
			content.append(SEPARATOR);
			content.append("Number of rows are different.");
			content.append(String.format("%nSource rows: %-40s Target rows: %1s%n", sourceListCount, destListCount));
		}
		gr.printAndWrite(content.toString());
	}

	public void customQueryAssertValueValidation(String targetSQL, String value) throws ValidationException {
		StringBuilder content = new StringBuilder();
		content.append(SEPARATOR);
		content.append("Validating given assert value on the target table");
		if (!targetData.getDataFromSQL(targetSQL).isEmpty()) {
			String tableValue = targetData.getDataFromSQL(targetSQL).get(0);
			if (tableValue.equalsIgnoreCase(value)) {
				content.append(SEPARATOR + "Assert value validation passed.");
			} else {
				content.append(SEPARATOR + "Assert value validation failed.");
			}
		} else {
			content.append(SEPARATOR + "Assert value validation failed.");
		}
		gr.printAndWrite(content.toString());
	}

	public static void main(String[] args) {
		
	}
}
