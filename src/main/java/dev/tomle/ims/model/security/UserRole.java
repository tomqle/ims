package dev.tomle.ims.model.security;

import dev.tomle.ims.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "userrole")
public class UserRole extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	@ManyToOne
	@JoinColumn(name = "roleid")
	private Role role;

	public UserRole() {}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
