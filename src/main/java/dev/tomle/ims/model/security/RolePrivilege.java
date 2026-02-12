package dev.tomle.ims.model.security;

import dev.tomle.ims.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "roleprivilege")
public class RolePrivilege extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "roleid")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "privilegeid")
	private Privilege privilege;

	public RolePrivilege() {}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
}
