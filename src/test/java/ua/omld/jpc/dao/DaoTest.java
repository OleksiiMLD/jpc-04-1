package ua.omld.jpc.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ua.omld.jpc.util.HibernateUtil;

/**
 * @author Oleksii Kostetskyi
 */
public abstract class DaoTest {

	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@BeforeEach
	void setUp() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@AfterEach
	void tearDown() {
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}

}
