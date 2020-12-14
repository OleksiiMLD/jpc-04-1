package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.omld.jpc.dao.MaterialDAO;
import ua.omld.jpc.entity.Material;

/**
 * Implements {@link MaterialDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
@Repository
public class MaterialDAOImpl extends HibernateGenericDAO<Material> implements MaterialDAO {

	@Autowired
	public MaterialDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
