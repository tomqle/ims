package dev.tomle.ims.interfaces.security.facade.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.UserService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.security.User;
import dev.tomle.ims.interfaces.security.facade.UserServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.UserDTO;
import dev.tomle.ims.interfaces.security.facade.dto.mapper.UserMapper;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceFacadeImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;

	@Override
	public Page<UserDTO> getUsers(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<User> users = userService.getUsers(pageNum, pageSize, sortBy, desc);
		Page<UserDTO> userDTOs = userMapper.toDto(users);

		LogUtil.exitMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", userDTOs);
		return userDTOs;
	}

	@Override
	public Page<UserDTO> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", pageNum, pageSize, sortBy, desc);

		Page<User> users = userService.getUsersByUsername(pageNum, pageSize, sortBy, desc, username);
		Page<UserDTO> userDTOs = userMapper.toDto(users);

		LogUtil.exitMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", userDTOs);
		return userDTOs;
	}

	@Override
	public UserDTO getUserById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getUserById(id)", id);

		UserDTO userDTO = userMapper.toDto(userService.getUserById(id));

		LogUtil.exitMethod(logger, getClassName(), "getUserById(id)", userDTO);
		return userDTO;
	}
	
	@Override
	public UserDTO getUserByUsername(String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUserByUsername(username)", username);

		UserDTO userDTO = userMapper.toDto(userService.getUserByUsername(username));

		LogUtil.exitMethod(logger, getClassName(), "getUserByUsername(username)", userDTO);
		return userDTO;
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		LogUtil.enterMethod(logger, getClassName(), "saveUser(userDTO)", user);

		UserDTO userSaved = userMapper.toDto(userService.saveUser(userMapper.toDomain(user)));

		LogUtil.exitMethod(logger, getClassName(), "saveUser(userDTO)", userSaved);
		return userSaved;
	}

	@Override
	public void deleteUser(UserDTO user) {
		LogUtil.enterMethod(logger, getClassName(), "deleteUser(userDTO)", user);

		userService.deleteUser(userMapper.toDomain(user));

		LogUtil.exitMethod(logger, getClassName(), "deleteUser(userDTO)");
	}

	@Override
	public void deleteUserById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteUserById(id)", id);

		userService.deleteUserById(id);

		LogUtil.exitMethod(logger, getClassName(), "deleteUserById(id)");
	}

	@Override
	public String login(String username) {
		return userService.login(username);
	}

	private String getClassName() {
		return UserServiceFacadeImpl.class.getName();
	}
}
