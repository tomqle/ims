package dev.tomle.ims.service.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.security.User;
import dev.tomle.ims.model.security.dto.UserDTO;
import dev.tomle.ims.model.security.dto.mapper.UserMapper;
import dev.tomle.ims.model.security.repository.UserRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserMapper userMapper;

	@Override
	public Page<UserDTO> getUsers(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<User> users = userRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, User.SORT_BY));
		Page<UserDTO> userDTOs = userMapper.toDto(users);

		LogUtil.exitMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", users);
		return userDTOs;
	}

	@Override
	public Page<UserDTO> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", pageNum, pageSize, sortBy, desc, username);

		Page<User> users = userRepository.findLikeUsername(username, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, User.SORT_BY));
		Page<UserDTO> userDTOs = userMapper.toDto(users);

		LogUtil.exitMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", users);
		return userDTOs;
	}

	@Override
	public UserDTO getUserById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getUsersById(id)", id);

		Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.isPresent() ? userOpt.get() : null;
		UserDTO userDTO = userMapper.toDto(user);

		LogUtil.exitMethod(logger, getClassName(), "getUsersById(id)", user);
		return userDTO;
	}
	
	@Override
	public UserDTO getUserByUsername(String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUserByUsername(username)", username);

		User user = userRepository.findByUsername(username);
		UserDTO userDTO = userMapper.toDto(user);

		LogUtil.exitMethod(logger, getClassName(), "getUserByUsername(username)", user);
		return userDTO;
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		LogUtil.enterMethod(logger, getClassName(), "saveUser(user)", user);

		User userSaved = userRepository.save(userMapper.toDomain(user));
		UserDTO userSavedDTO = userMapper.toDto(userSaved);

		LogUtil.exitMethod(logger, getClassName(), "saveUser(user)", userSaved);
		return userSavedDTO;
	}

	@Override
	public void deleteUser(UserDTO user) {
		LogUtil.enterMethod(logger, getClassName(), "deleteUser(user)", user);
		userRepository.delete(userMapper.toDomain(user));
		LogUtil.exitMethod(logger, getClassName(), "deleteUser(user)");
	}

	@Override
	public void deleteUserById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteUser(id)", id);
		userRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteUser(id)");
	}
	
	private String getClassName() {
		return UserServiceImpl.class.getName();
	}

	@Override
	public String login(String username) {
		return jwtUtils.generateJwtToken(username);
	}
}
