package ua.omld.jpc.testdata;

import ua.omld.jpc.entity.Material;
import ua.omld.jpc.opencsv.CSVDataReader;

/**
 * @author Oleksii Kostetskyi
 */
public class MaterialTestData extends CSVDataReader<Material> {

	public static final MaterialTestData GENERAL_MATERIAL_DATA = new MaterialTestData("testdata/material_data.csv");

	public static final Long ID_TO_FIND = 2L;
	public static final Long ID_TO_DELETE = 3L;
	public static final Long NONEXISTENT_ID = 30000L;
	public static final String STRING_55_CHARS = "Very very long string 345678901234567890123456789012345";


	public MaterialTestData(String dataFilePath) {
		super(dataFilePath);
	}
}
