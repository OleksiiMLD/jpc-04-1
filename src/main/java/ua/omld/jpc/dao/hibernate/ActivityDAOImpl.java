package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ua.omld.jpc.dao.ActivityDAO;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implements {@link ActivityDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
public class ActivityDAOImpl extends HibernateGenericDAO<Activity> implements ActivityDAO {

	private static final String FIND_BY_USER_AND_BUILDING = "SELECT a.* FROM activity as a" +
			" INNER JOIN building b on a.building_id = b.inst_id" +
			" INNER JOIN report r on b.report_id = r.inst_id" +
			" WHERE a.building_id = :b_id AND r.user_id = :u_id";
	private static final String TOTAL_ACTIVITIES_PRICE_BY_REPORT = "SELECT sum(a.price) FROM Activity a" +
			" INNER JOIN a.building b" +
			" WHERE b.report = :report";
	private static final String TOTAL_ACTIVITIES_PRICE_BY_USER = "SELECT sum(a.price) FROM activity as a" +
			" INNER JOIN building b on a.building_id = b.inst_id" +
			" INNER JOIN report r on b.report_id = r.inst_id" +
			" WHERE r.user_id = :u_id";

	public ActivityDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * Returns list of found {@link Activity Activities} by the given {@link User} and {@link Building}
	 *
	 * @param user     given user
	 * @param building given building
	 * @return list of found activities
	 */
	@Override
	public List<Activity> findAllByUserAndBuilding(User user, Building building) {
		NativeQuery<Activity> query = getCurrentSession().createNativeQuery(FIND_BY_USER_AND_BUILDING, Activity.class);
		query.setParameter("b_id", building.getId());
		query.setParameter("u_id", user.getId());
		return query.getResultList();
	}

	/**
	 * Return total price of all {@link Activity Activities} for the given {@link Building}
	 *
	 * @param building given building
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByBuilding(Building building) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
		Root<Activity> fromActivity = cq.from(Activity.class);
		cq.select(cb.sum(fromActivity.get("price").as(BigDecimal.class))).where(cb.equal(fromActivity.get("building"),
				building));
		return getCurrentSession().createQuery(cq).getSingleResult();
	}

	/**
	 * Return total price of all {@link Activity Activities} for the given {@link Report}
	 *
	 * @param report given report
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByReport(Report report) {
		Query<BigDecimal> query = getCurrentSession().createQuery(TOTAL_ACTIVITIES_PRICE_BY_REPORT, BigDecimal.class);
		query.setParameter("report", report);
		return query.getSingleResult();
	}

	/**
	 * Return total price of all {@link Activity Activities} for the given {@link User}
	 *
	 * @param user given user
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByUser(User user) {
		Query<BigDecimal> query = getCurrentSession().createNativeQuery(TOTAL_ACTIVITIES_PRICE_BY_USER);
		query.setParameter("u_id", user.getId());
		return query.getSingleResult();
	}
}