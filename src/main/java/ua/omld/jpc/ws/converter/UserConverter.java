package ua.omld.jpc.ws.converter;

import jpc.omld.ua.ObjectFactory;
import jpc.omld.ua.User;
import org.springframework.stereotype.Component;

/**
 * @author Oleksii Kostetskyi
 */
@Component("ws.UserConverter")
public class UserConverter extends Converter<User, ua.omld.jpc.entity.User> {

	public UserConverter(ObjectFactory objectFactory) {
		super(UserConverter::convertToEntity, UserConverter::convertToJAXB);
		UserConverter.objectFactory = objectFactory;
	}

	private static User convertToJAXB(ua.omld.jpc.entity.User user) {
		if (user == null) {
			return null;
		}
		User jaxbUser = objectFactory.createUser();
		jaxbUser.setId(objectFactory.createUserId(user.getId()));
		jaxbUser.setUserName(objectFactory.createUserUserName(user.getUserName()));
		jaxbUser.setLastName(objectFactory.createUserLastName(user.getLastName()));
		jaxbUser.setEmail(objectFactory.createUserEmail(user.getEmail()));
		if (user.getEmailBackup() != null) {
			jaxbUser.setEmailBackup(objectFactory.createUserEmailBackup(user.getEmailBackup()));
		}
		jaxbUser.setTelephoneNumber(objectFactory.createUserTelephoneNumber(user.getTelephoneNumber()));
		if (user.getTelephoneNumberBackUp() != null) {
			jaxbUser.setTelephoneNumberBackUp(objectFactory.createUserTelephoneNumberBackUp(user.getTelephoneNumberBackUp()));
		}
		return jaxbUser;
	}

	private static ua.omld.jpc.entity.User convertToEntity(User jaxbUser) {
		if (jaxbUser == null) {
			return null;
		}
		ua.omld.jpc.entity.User user = new ua.omld.jpc.entity.User();
		user.setId(jaxbUser.getId().getValue());
		user.setUserName(jaxbUser.getUserName().getValue());
		user.setLastName(jaxbUser.getLastName().getValue());
		user.setEmail(jaxbUser.getEmail().getValue());
		user.setEmailBackup(jaxbUser.getEmailBackup().getValue());
		user.setTelephoneNumber(jaxbUser.getTelephoneNumber().getValue());
		user.setTelephoneNumberBackUp(jaxbUser.getTelephoneNumberBackUp().getValue());
		return user;
	}
}
