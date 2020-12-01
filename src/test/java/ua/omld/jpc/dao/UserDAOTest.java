package ua.omld.jpc.dao;

import ua.omld.jpc.dao.hibernate.UserDAOImpl;

/**
 * @author Oleksii Kostetskyi
 */
class UserDAOTest extends DaoTest {

	private UserDAO userDAO = new UserDAOImpl(sessionFactory);

//	@Test
//	void read() {
//		User actualUser = userDAO.findById(1L);
//		assertNotNull(actualUser);
//		assertEquals(GENERAL_USER_DATA.get("USER_1").getUserName(), actualUser.getUserName());
//	}

//	@Test
//	void create() {
//		assertDoesNotThrow(() -> userDAO.create(User.builder().withUserName("user").withEmail("user@email.com")
//				.withTelephoneNumber("+380971234567").build()));
//	}
}