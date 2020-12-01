package ua.omld.jpc.service;

import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import java.util.List;

/**
 * Provides Service interface for {@link Report}
 *
 * @author Oleksii Kostetskyi
 */
public interface ReportService {

	/**
	 * Returns list of all {@link Report Reports} for the given {@link User}
	 *
	 * @param user user for witch search reports
	 * @return list of user's reports
	 */
	List<Report> findAllByUser(User user);
}
