package dev.tomle.ims.interfaces.security.facade.dto;

import java.io.Serializable;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class UserDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	//private String password;
	private boolean enabled;
	private boolean tokenExpired;
	private List<RoleBasicDTO> roles;
	
	public UserDTO() {
		id = 0;
		firstName = null;
		lastName = null;
		username = null;
		email = null;
		//password = null;
		enabled = false;
		tokenExpired = true;
		setRoles(null);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public List<RoleBasicDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBasicDTO> roles) {
		this.roles = roles;
	}
}
