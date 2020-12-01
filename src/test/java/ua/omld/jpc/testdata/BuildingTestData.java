package ua.omld.jpc.testdata;

import ua.omld.jpc.entity.Building;
import ua.omld.jpc.opencsv.CSVDataReader;

import java.math.BigDecimal;

/**
 * @author Oleksii Kostetskyi
 */
public class BuildingTestData extends CSVDataReader<Building> {

	public static final BuildingTestData GENERAL_BUILDING_DATA = new BuildingTestData("testdata/building_data.csv");
	public static final BuildingTestData BUILDING_DATA_FOR_PRICE = new BuildingTestData("testdata/building_data_price" +
			".csv");

	public static final BigDecimal TOTAL_ACTIVITIES_PRICE = BigDecimal.valueOf(20000.0);

	protected BuildingTestData(String dataFilePath) {
		super(dataFilePath);
	}
}
