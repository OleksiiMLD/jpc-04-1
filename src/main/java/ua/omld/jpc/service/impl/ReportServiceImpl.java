package ua.omld.jpc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.omld.jpc.dao.ReportDAO;
import ua.omld.jpc.dao.UserDAO;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;
import ua.omld.jpc.service.ReportService;

import java.util.List;

/**
 * Implements {@link ReportService} interface.
 *
 * @author Oleksii Kostetskyi
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String PROVIDE_USER_ID = "Please provide user id.";
	private static final String WRONG_USER_ID = "Wrong user id: ";

	private ReportDAO reportDAO;
	private UserDAO userDAO;

	@Autowired
	public ReportServiceImpl(ReportDAO reportDAO, UserDAO userDAO) {
		this.reportDAO = reportDAO;
		this.userDAO = userDAO;
	}

	/**
	 * Returns list of all {@link Report Reports} for {@link User} with the given id.
	 *
	 * @param userId id of user for which search reports
	 * @return list of user's reports
	 */
	@Override
	public List<Report> findAllByUserId(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException(PROVIDE_USER_ID);
		}
		return reportDAO.executeInsideTransaction(
				() -> {
					User user = userDAO.findById(userId);
					if (user == null) {
						LOGGER.debug(WRONG_USER_ID + userId);
						throw new IllegalArgumentException(WRONG_USER_ID + userId);
					}
					return reportDAO.findAllByUser(user);
				},
				"Error find user reports."
		);
	}
}