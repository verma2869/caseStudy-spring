package com.impetus.validator.generate.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateReport {
	private static final Logger logger = Logger.getLogger(GenerateReport.class.getName());

	private void writeToFile(String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		String home = System.getProperty("user.home");
		File dir = new File(home + "\\Validation\\");
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		try {
			File file = new File(dir + "\\ValidationReport.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}
	
	public void printAndWrite(String content) {
		writeToFile(content);
		System.out.println(content);
	}
}