package dev.tomle.ims.service.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.dto.UserDTO;

public interface UserService {
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public Page<UserDTO> getUsers(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public Page<UserDTO> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public UserDTO getUserById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.USER_READ + "')")
	public UserDTO getUserByUsername(String username);
	@PreAuthorize("hasAuthority('" + Privilege.USER_WRITE + "')")
	public UserDTO saveUser(UserDTO user);
	@PreAuthorize("hasAuthority('" + Privilege.USER_DELETE + "')")
	public void deleteUser(UserDTO user);
	@PreAuthorize("hasAuthority('" + Privilege.USER_DELETE + "')")
	public void deleteUserById(long id);

	public String login(String username);
}
