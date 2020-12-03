package ua.omld.jpc.dao;

import org.junit.jupiter.api.Test;
import ua.omld.jpc.dao.hibernate.ReportDAOImpl;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.omld.jpc.testdata.UserTestData.NONEXISTENT_ID;
import static ua.omld.jpc.testdata.UserTestData.USER_WITHOUT_REPORTS;
import static ua.omld.jpc.testdata.UserTestData.USER_WITH_ONE_REPORT;
import static ua.omld.jpc.testdata.UserTestData.USER_WITH_THREE_REPORTS;

/**
 * @author Oleksii Kostetskyi
 */
class ReportDAOTest extends DaoTest {

	private ReportDAO reportDAO = new ReportDAOImpl(sessionFactory);

	@Test
	void findAllByUser_userWithThreeReports_returnsThreeReports() {
		List<Report> reports = reportDAO.findAllByUser(USER_WITH_THREE_REPORTS);
		assertEquals(3, reports.size(), "Wrong reports count.");
	}

	@Test
	void findAllByUser_userWithOneReport_returnsOneReport() {
		List<Report> reports = reportDAO.findAllByUser(USER_WITH_ONE_REPORT);
		assertEquals(1, reports.size(), "Wrong reports count.");
	}

	@Test
	void findAllByUser_userWithoutReports_returnsNoReports() {
		List<Report> reports = reportDAO.findAllByUser(USER_WITHOUT_REPORTS);
		assertEquals(0, reports.size(), "There are no reports for the user.");
	}

	@Test
	void findAllByUser_nonexistentUser_returnsNoReports() {
		User user = new User();
		user.setId(NONEXISTENT_ID);
		List<Report> reports = reportDAO.findAllByUser(user);
		assertEquals(0, reports.size(), "Nonexistent user - no reports.");
	}

	@Test
	void findAllByUser_transientUser_throwsException() {
		User user = new User();
		assertThrows(IllegalStateException.class, () -> reportDAO.findAllByUser(user), "Transient user.") ;
	}
}