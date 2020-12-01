package ua.omld.jpc.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple tests of configuration.
 *
 * @author Oleksii Kostetskyi
 */
class HibernateUtilTest {

	@Test
	void getSessionFactory_onRightConfiguration_factoryBuilt() {
		assertNotNull(HibernateUtil.getSessionFactory());
	}
}