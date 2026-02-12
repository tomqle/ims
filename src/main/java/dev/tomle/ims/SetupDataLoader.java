package dev.tomle.ims;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.Role;
import dev.tomle.ims.model.security.User;
import dev.tomle.ims.model.security.repository.PrivilegeRepository;
import dev.tomle.ims.model.security.repository.RoleRepository;
import dev.tomle.ims.model.security.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;


        Privilege batchRead = createPrivilegeIfNotFound(Privilege.BATCH_READ);
        Privilege privilegeRead = createPrivilegeIfNotFound(Privilege.PRIVILEGE_READ);
        Privilege privilegeWrite = createPrivilegeIfNotFound(Privilege.PRIVILEGE_WRITE);
        Privilege purchaseOrderRead = createPrivilegeIfNotFound(Privilege.PURCHASE_ORDER_READ);
        Privilege purchaseOrderWrite = createPrivilegeIfNotFound(Privilege.PURCHASE_ORDER_WRITE);
        //Privilege purchaseOrderReceive = createPrivilegeIfNotFound(Privilege.PURCHASE_ORDER_RECEIVE);
        //Privilege purchaseOrderDelete = createPrivilegeIfNotFound(Privilege.PURCHASE_ORDER_DELETE);
        Privilege roleRead = createPrivilegeIfNotFound(Privilege.ROLE_READ);
        Privilege roleWrite = createPrivilegeIfNotFound(Privilege.ROLE_WRITE);
        Privilege salesOrderRead = createPrivilegeIfNotFound(Privilege.SALES_ORDER_READ);
        //Privilege salesOrderWrite = createPrivilegeIfNotFound(Privilege.SALES_ORDER_WRITE);
        //Privilege salesOrderAllocate = createPrivilegeIfNotFound(Privilege.SALES_ORDER_ALLOCATE);
        //Privilege salesOrderDelete = createPrivilegeIfNotFound(Privilege.SALES_ORDER_DELETE);
        Privilege userRead = createPrivilegeIfNotFound(Privilege.USER_READ);
        Privilege userWrite = createPrivilegeIfNotFound(Privilege.USER_WRITE);
        
        Privilege productRead = createPrivilegeIfNotFound(Privilege.PRODUCT_READ);
        Privilege productWrite = createPrivilegeIfNotFound(Privilege.PRODUCT_WRITE);
        //Privilege productDelete = createPrivilegeIfNotFound(Privilege.PRODUCT_DELETE);
 
        List<Privilege> adminPrivileges = Arrays.asList(purchaseOrderRead,
        												purchaseOrderWrite,
        												salesOrderRead,
        												batchRead,
        												userRead,
        												userWrite,
        												roleRead,
        												roleWrite,
        												privilegeRead,
        												privilegeWrite,
        												productRead,
        												productWrite
        												);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(purchaseOrderRead, productRead));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");
        
        createUserIfNotFound("user", userRole);
        createUserIfNotFound("admin", adminRole);

        alreadySetup = true;
	}
	
	@Transactional
	private User createUserIfNotFound(String username, Role role) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
        	user = new User();
        	user.setFirstName("first");
        	user.setLastName("last");
        	user.setPassword(passwordEncoder.encode("password"));
        	user.setUsername(username);
        	user.setEmail("admin@test.com");
        	user.setRoles(Arrays.asList(role));
        	user.setEnabled(true);
        	userRepository.save(user);
        }

        return user;
	}

	@Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(
      String name, List<Privilege> privileges) {
 
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
