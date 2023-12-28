package dev.tomle.ims.interfaces.security.facade;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.security.facade.dto.UserDTO;

public interface UserServiceFacade {
	public Page<UserDTO> getUsers(int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<UserDTO> getUsersByUsername(int pageNum, int pageSize, String sortBy, boolean desc, String username);
	public UserDTO getUserById(long id);
	public UserDTO getUserByUsername(String username);
	public UserDTO saveUser(UserDTO user);
	public void deleteUser(UserDTO user);
	public void deleteUserById(long id);
	public String login(String username);
}
