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
	 * Returns list of all {@link Report Reports} for the {@link User} with the given id.
	 *
	 * @param userId id of user for which search reports
	 * @return list of user's reports
	 */
	List<Report> findAllByUserId(Long userId);
}
