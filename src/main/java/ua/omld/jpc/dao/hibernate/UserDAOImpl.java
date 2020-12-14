package ua.omld.jpc.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.omld.jpc.dao.UserDAO;
import ua.omld.jpc.entity.User;

/**
 * Implements {@link UserDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
@Repository
public class UserDAOImpl extends HibernateGenericDAO<User> implements UserDAO {

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
