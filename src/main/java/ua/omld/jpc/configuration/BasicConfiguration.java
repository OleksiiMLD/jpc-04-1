package ua.omld.jpc.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.omld.jpc.util.HibernateUtil;

/**
 * @author Oleksii Kostetskyi
 */
@Configuration
@ComponentScan(basePackages = "ua.omld.jpc")
public class BasicConfiguration {

	@Bean
	public SessionFactory sessionFactory() {
		return HibernateUtil.getSessionFactory();
	}
}
