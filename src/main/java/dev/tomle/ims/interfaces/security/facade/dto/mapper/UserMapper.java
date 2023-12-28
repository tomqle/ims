package dev.tomle.ims.interfaces.security.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.security.User;
import dev.tomle.ims.interfaces.security.facade.dto.UserDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class UserMapper implements BaseEntityMapper<User, UserDTO> {

	@Autowired
	private RoleBasicMapper roleBasicMapper;

	@Override
	public UserDTO toDto(User user) {
		UserDTO userDTO = null;
		if(user != null) {
			userDTO = new UserDTO();
			setBaseEntityMembers(user, userDTO);
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setUsername(user.getUsername());
			userDTO.setEmail(user.getEmail());
			//userDTO.setPassword(user.getPassword());
			userDTO.setEnabled(user.isEnabled());
			userDTO.setTokenExpired(user.isTokenExpired());
			userDTO.setRoles(roleBasicMapper.toDto(user.getRoles()));
		}
		
		return userDTO;
	}
	
	@Override
	public User toDomain(UserDTO userDTO) {
		User user= null;
		if(userDTO != null) {
			user = new User();
			setBaseEntityMembers(userDTO, user);
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setUsername(userDTO.getUsername());
			user.setEmail(userDTO.getEmail());
			//user.setPassword(userDTO.getPassword());
			user.setEnabled(userDTO.isEnabled());
			user.setTokenExpired(userDTO.isTokenExpired());
			user.setRoles(roleBasicMapper.toDomain(userDTO.getRoles()));
		}
		return user;
	}
}
