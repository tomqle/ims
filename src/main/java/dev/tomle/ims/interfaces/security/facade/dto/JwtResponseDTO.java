package dev.tomle.ims.interfaces.security.facade.dto;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;
	private UserDTO user;
	
	public JwtResponseDTO() {}
	
	public JwtResponseDTO(String token, UserDTO user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
