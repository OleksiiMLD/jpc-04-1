package ua.omld.jpc.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ua.omld.jpc.dto.UserDto;
import ua.omld.jpc.entity.User;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class UserConverter extends Converter<UserDto, User> {

	public UserConverter() {
		super(UserConverter::convertToEntity, UserConverter::convertToDto);
	}

	private static UserDto convertToDto(User user) {
		if (user == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto, "reports");
		return userDto;
	}

	private static User convertToEntity(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
}
