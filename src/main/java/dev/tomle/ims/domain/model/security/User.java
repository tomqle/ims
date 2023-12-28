package dev.tomle.ims.domain.model.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

@Entity(name = "USERS")
public class User extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "username"));

	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String username;
	private String email;
	private String password;
	private boolean enabled;
	private boolean tokenExpired;
	@ManyToMany
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(
			name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "id")) 
    private List<Role> roles;
	
	public User() {}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
