package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.omld.jpc.dao.ReportDAO;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implements {@link ReportDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
public class ReportDAOImpl extends HibernateGenericDAO<Report> implements ReportDAO {

	public ReportDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * Returns list of all {@link Report Reports} for the given {@link User}
	 *
	 * @param user user for witch search reports
	 * @return list of found reports
	 */
	@Override
	public List<Report> findAllByUser(User user) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Report> cq = cb.createQuery(Report.class);
		Root<Report> fromReport = cq.from(Report.class);
		fromReport.fetch("buildings", JoinType.LEFT).fetch("activities", JoinType.LEFT);
		cq.where(cb.equal(fromReport.get("user"), user)).distinct(true);
		return getCurrentSession().createQuery(cq).getResultList();
	}
}
