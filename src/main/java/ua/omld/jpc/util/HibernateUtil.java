package ua.omld.jpc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

/**
 * Utilities class for work with Hibernate objects.
 *
 * @author Oleksii Kostetskyi
 */
public class HibernateUtil {

	private static volatile SessionFactory sessionFactory;

	private HibernateUtil() {
		// Non-instantiable util class
	}

	/**
	 * Create a {@link SessionFactory} configured for application.
	 * Returns Hibernate's {@code SessionFactory}. If it is not created yet creates one.
	 *
	 * @return The build {@link SessionFactory}
	 *
	 * @throws HibernateException with invalid configuration or invalid mapping
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			synchronized (HibernateUtil.class) {
				if (sessionFactory == null) {
					sessionFactory = new Configuration()
							.addAnnotatedClass(User.class)
							.addAnnotatedClass(Material.class)
							.addAnnotatedClass(Activity.class)
							.addAnnotatedClass(Building.class)
							.addAnnotatedClass(Report.class)
							.buildSessionFactory();
				}
			}
		}
		return sessionFactory;
	}
}
