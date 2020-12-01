package ua.omld.jpc.testdata;

import ua.omld.jpc.entity.User;
import ua.omld.jpc.opencsv.CSVDataReader;

/**
 * @author Oleksii Kostetskyi
 */
public class UserTestData extends CSVDataReader<User> {

	public static final UserTestData GENERAL_USER_DATA = new UserTestData("testdata/user_data.csv");

	public static final Long NONEXISTENT_ID = 30000L;
	public static final User USER_WITH_THREE_REPORTS = GENERAL_USER_DATA.get("USER_1");
	public static final User USER_WITH_ONE_REPORT = GENERAL_USER_DATA.get("USER_2");
	public static final User USER_WITHOUT_REPORTS = GENERAL_USER_DATA.get("USER_3");

	public UserTestData(String dataFilePath) {
		super(dataFilePath);
	}
}
