package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.omld.jpc.dao.MaterialDAO;
import ua.omld.jpc.entity.Material;

/**
 * Implements {@link MaterialDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
public class MaterialDAOImpl extends HibernateGenericDAO<Material> implements MaterialDAO {

	public MaterialDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
