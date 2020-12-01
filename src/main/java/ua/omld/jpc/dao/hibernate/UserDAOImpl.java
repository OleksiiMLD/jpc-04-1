package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.omld.jpc.dao.UserDAO;
import ua.omld.jpc.entity.User;

/**
 * Implements {@link UserDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
public class UserDAOImpl extends HibernateGenericDAO<User> implements UserDAO {

	public UserDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
