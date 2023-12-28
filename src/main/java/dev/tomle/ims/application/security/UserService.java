package dev.tomle.ims.application.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.domain.model.security.User;

public interface UserService {
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public Page<User> getUsers(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public Page<User> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public User getUserById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public User getUserByUsername(String username);
	@PreAuthorize("hasAuthority('" + Privilege.USER_WRITE + "')")
	public User saveUser(User user);
	@PreAuthorize("hasAuthority('" + Privilege.USER_DELETE + "')")
	public void deleteUser(User user);
	@PreAuthorize("hasAuthority('" + Privilege.USER_DELETE + "')")
	public void deleteUserById(long id);

	public String login(String username);
}
