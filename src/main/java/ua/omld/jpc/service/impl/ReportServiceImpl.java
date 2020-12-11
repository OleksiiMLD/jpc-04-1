package ua.omld.jpc.service.impl;

import ua.omld.jpc.dao.ReportDAO;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;
import ua.omld.jpc.service.ReportService;

import java.util.List;

/**
 * Implements {@link ReportService} interface.
 *
 * @author Oleksii Kostetskyi
 */
public class ReportServiceImpl implements ReportService {

	private ReportDAO reportDAO;

	public ReportServiceImpl(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	/**
	 * Returns list of all {@link Report Reports} for the given {@link User}
	 *
	 * @param user user for which search reports
	 * @return list of user's reports
	 */
	@Override
	public List<Report> findAllByUser(User user) {
		return reportDAO.findAllByUser(user);
	}
}
