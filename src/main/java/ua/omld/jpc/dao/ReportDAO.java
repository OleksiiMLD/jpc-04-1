package ua.omld.jpc.dao;

import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import java.util.List;

/**
 * Provides DAO interface for {@link Report}
 *
 * @author Oleksii Kostetskyi
 */
public interface ReportDAO extends GenericDAO<Report, Long> {

	/**
	 * Returns list of all {@link Report Reports} for the given {@link User}
	 *
	 * @param user user for which search reports
	 * @return list of found reports
	 */
	List<Report> findAllByUser(User user);
}
