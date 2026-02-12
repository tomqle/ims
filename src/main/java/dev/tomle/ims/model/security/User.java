package dev.tomle.ims.model.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tomle.ims.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity(name = "users")
public class User extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "username"));

	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(unique=true)
	private String username;
	private String email;
	private String password;
	private boolean enabled;
	@Column(name = "tokenexpired")
	private boolean tokenExpired;
	/*@ManyToMany
	@JoinTable(
		name = "userrole",
		joinColumns = @JoinColumn(
			name = "userid", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(
			name = "roleid", referencedColumnName = "id")) 
    private List<Role> roles;*/
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserRole> userRoles;
	
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
		return userRoles.stream().map(x -> x.getRole()).toList();
	}
	
	public void setRoles(List<Role> roles) {
		userRoles = new ArrayList<>();
		for(Role r: roles) {
			UserRole userRole = new UserRole();
			userRole.setUser(this);
			userRole.setRole(r);
			userRoles.add(userRole);
		}
	}
}
