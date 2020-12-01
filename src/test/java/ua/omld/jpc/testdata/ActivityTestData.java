package ua.omld.jpc.testdata;

import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.opencsv.CSVDataReader;

import java.math.BigDecimal;

/**
 * @author Oleksii Kostetskyi
 */
public class ActivityTestData extends CSVDataReader<Activity> {

	public static final ActivityTestData GENERAL_ACTIVITY_DATA = new ActivityTestData("testdata/activity_data.csv");
	public static final ActivityTestData BUILDING_2_ACTIVITY_DATA = new ActivityTestData("testdata/activity_data_building2.csv");

	public static final BigDecimal TOTAL_ACTIVITIES_PRICE_FOR_BUILDING_2 = BigDecimal.valueOf(24000.0);
	public static final BigDecimal TOTAL_ACTIVITIES_PRICE_FOR_REPORT_3 = BigDecimal.valueOf(66819.0);
	public static final BigDecimal TOTAL_ACTIVITIES_PRICE_FOR_USER_1 = BigDecimal.valueOf(8200.0);

	public ActivityTestData(String dataFilePath) {
		super(dataFilePath);
	}
}
