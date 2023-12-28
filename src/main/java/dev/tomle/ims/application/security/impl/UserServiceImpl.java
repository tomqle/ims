package dev.tomle.ims.application.security.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.UserService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.security.User;
import dev.tomle.ims.infrastructure.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public Page<User> getUsers(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<User> users = userRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, User.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getUsers(pageNum, pageSize, sortBy, desc)", users);
		return users;
	}

	@Override
	public Page<User> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", pageNum, pageSize, sortBy, desc, username);

		Page<User> users = userRepository.findLikeUsername(username, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, User.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getUsersByUsername(pageNum, pageSize, sortBy, desc, username)", users);
		return users;
	}

	@Override
	public User getUserById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getUsersById(id)", id);

		Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.isPresent() ? userOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getUsersById(id)", user);
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		LogUtil.enterMethod(logger, getClassName(), "getUserByUsername(username)", username);

		User user = userRepository.findByUsername(username);

		LogUtil.exitMethod(logger, getClassName(), "getUserByUsername(username)", user);
		return user;
	}

	@Override
	public User saveUser(User user) {
		LogUtil.enterMethod(logger, getClassName(), "saveUser(user)", user);

		User userSaved = userRepository.save(user);

		LogUtil.exitMethod(logger, getClassName(), "saveUser(user)", userSaved);
		return userSaved;
	}

	@Override
	public void deleteUser(User user) {
		LogUtil.enterMethod(logger, getClassName(), "deleteUser(user)", user);
		userRepository.delete(user);
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
