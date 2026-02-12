package dev.tomle.ims.model.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tomle.ims.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Role extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "name"));

	private String name;
    /*@ManyToMany(mappedBy = "roles")
    private List<User> users;*/
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserRole> userRoles;
    
	/*@ManyToMany
    @JoinTable(
        name = "roleprivilege", 
        joinColumns = @JoinColumn(
          name = "roleid", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilegeid", referencedColumnName = "id"))
    private List<Privilege> privileges;*/

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<RolePrivilege> rolePrivileges;

    public Role() {}

    public Role(String name) {
    	this.name = name;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return userRoles.stream().map(x -> x.getUser()).toList();
	}
	
	public void setUsers(List<User> users) {
		userRoles = new ArrayList<>();
		for(User u: users) {
			UserRole userRole = new UserRole();
			userRole.setRole(this);
			userRole.setUser(u);
			userRoles.add(userRole);
		}
	}
	
	public List<Privilege> getPrivileges() {
		return rolePrivileges.stream().map(x -> x.getPrivilege()).toList();
	}
	
	public void setPrivileges(List<Privilege> privileges) {
		rolePrivileges = new ArrayList<>();
		for(Privilege p: privileges) {
			RolePrivilege rolePrivilege = new RolePrivilege();
			rolePrivilege.setRole(this);
			rolePrivilege.setPrivilege(p);
			rolePrivileges.add(rolePrivilege);
		}
	}
}
