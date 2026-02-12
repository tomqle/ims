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
public class Privilege extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "name"));

	private String name;
	/*@ManyToMany(mappedBy = "privileges")
	private List<Role> roles;*/

	@OneToMany(mappedBy = "privilege", cascade = CascadeType.ALL)
	private List<RolePrivilege> rolePrivileges;
	 
	public Privilege() {}
	
	public Privilege(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Role> getRoles() {
		return rolePrivileges.stream().map(x -> x.getRole()).toList();
	}
	
	public void setRoles(List<Role> roles) {
		rolePrivileges = new ArrayList<>();
		for(Role r: roles) {
			RolePrivilege rolePrivilege = new RolePrivilege();
			rolePrivilege.setRole(r);
			rolePrivilege.setPrivilege(this);
			rolePrivileges.add(rolePrivilege);
		}
	}

	public static final String BATCH_READ = "BATCH_READ";
	public static final String CUSTOMER_ORDER_READ = "CUSTOMER_ORDER_READ";
	public static final String CUSTOMER_ORDER_WRITE = "CUSTOMER_ORDER_WRITE";
	public static final String CUSTOMER_ORDER_DELETE = "CUSTOMER_ORDER_DELETE";
	public static final String PRIVILEGE_READ = "PRIVILEGE_READ";
	public static final String PRIVILEGE_WRITE = "PRIVILEGE_WRITE";
	public static final String PRIVILEGE_DELETE = "PRIVILEGE_DELETE";
	public static final String PRODUCT_READ = "PRODUCT_READ";
	public static final String PRODUCT_WRITE = "PRODUCT_WRITE";
	public static final String PRODUCT_DELETE = "PRODUCT_DELETE";
	public static final String PURCHASE_ORDER_READ = "PURCHASE_ORDER_READ";
	public static final String PURCHASE_ORDER_WRITE = "PURCHASE_ORDER_WRITE";
	public static final String PURCHASE_ORDER_RECEIVE = "PURCHASE_ORDER_RECEIVE";
	public static final String PURCHASE_ORDER_DELETE = "PURCHASE_ORDER_DELETE";
	public static final String ROLE_READ = "ROLE_READ";
	public static final String ROLE_WRITE = "ROLE_WRITE";
	public static final String ROLE_DELETE = "ROLE_DELETE";
	public static final String SALES_ORDER_READ = "SALES_ORDER_READ";
	public static final	String SALES_ORDER_WRITE = "SALES_ORDER_WRITE";
	public static final String SALES_ORDER_ALLOCATE = "SALES_ORDER_ALLOCATE";
	public static final String SALES_ORDER_DELETE = "SALES_ORDER_DELETE";
	public static final String SUPPLIER_ORDER_READ = "SUPPLIER_ORDER_READ";
	public static final String SUPPLIER_ORDER_WRITE = "SUPPLIER_ORDER_WRITE";
	public static final String SUPPLIER_ORDER_DELETE = "SUPPLIER_ORDER_DELETE";
	public static final String USER_READ = "USER_READ";
	public static final String USER_WRITE = "USER_WRITE";
	public static final String USER_DELETE = "USER_DELETE";
}
