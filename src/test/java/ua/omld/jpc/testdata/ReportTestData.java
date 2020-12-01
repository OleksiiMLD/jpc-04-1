package ua.omld.jpc.testdata;

import ua.omld.jpc.entity.Report;
import ua.omld.jpc.opencsv.CSVDataReader;

/**
 * @author Oleksii Kostetskyi
 */
public class ReportTestData extends CSVDataReader<Report> {

	public static final ReportTestData GENERAL_REPORT_DATA = new ReportTestData("testdata/report_data.csv");

	public ReportTestData(String dataFilePath) {
		super(dataFilePath);
	}
}
