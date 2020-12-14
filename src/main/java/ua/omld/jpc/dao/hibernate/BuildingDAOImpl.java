package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.omld.jpc.dao.BuildingDAO;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.exception.DAOException;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implements {@link BuildingDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
@Repository
public class BuildingDAOImpl extends HibernateGenericDAO<Building> implements BuildingDAO {

	private static final String FIND_ALL_BY_TOTAL_PRICE = "SELECT b FROM Building b WHERE TRUE = " +
			" (SELECT SUM(a.price) > :t_price FROM Activity a WHERE a MEMBER OF b.activities )";
	private static final String INACTIVATE_ALL_BY_TOTAL_PRICE = "UPDATE building b SET is_active = FALSE WHERE " +
			" (SELECT sum(a.price) > :price FROM activity As a WHERE a.building_id = b.inst_id) = TRUE";

	@Autowired
	public BuildingDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * Returns list of all {@link Building Buildings} for which total price
	 * of all activities is more than specified value.
	 *
	 * @param price given total price of activities
	 * @return list of buildings
	 */
	@Override
	public List<Building> findAllByGreaterTotalActivitiesPrice(BigDecimal price) {
		try {
			Query<Building> query = getCurrentSession().createQuery(FIND_ALL_BY_TOTAL_PRICE, Building.class);
			query.setParameter("t_price", price);
			return query.getResultList();
		} catch (PersistenceException e) {
			logger.error("Error find buildings: " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Sets all {@link Building Buildings} to non-active state for which total price
	 * of all activities is more than specified value. Returns list of updated
	 * {@link Building buildings}.
	 *
	 * @param price given total price of activities
	 * @return number of updated buildings
	 */
	@Override
	public Integer inactivateAllByGreaterTotalActivitiesPrice(BigDecimal price) {
		try {
			getCurrentSession().flush();
			Query query = getCurrentSession().createNativeQuery(INACTIVATE_ALL_BY_TOTAL_PRICE);
			query.setParameter("price", price);
			return query.executeUpdate();
		} catch (PersistenceException e) {
			logger.error("Error inactivate buildings: " + e.getMessage());
			throw new DAOException(e);
		}
	}
}