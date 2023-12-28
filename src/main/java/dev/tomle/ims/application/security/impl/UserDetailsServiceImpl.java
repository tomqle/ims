package dev.tomle.ims.application.security.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.domain.model.security.Role;
import dev.tomle.ims.domain.model.security.User;
import dev.tomle.ims.infrastructure.security.repository.RoleRepository;
import dev.tomle.ims.infrastructure.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
	        if (user == null) {
	            return new org.springframework.security.core.userdetails.User(
	              " ", " ", true, true, true, true, 
	              getAuthorities(Arrays.asList(
	                roleRepository.findByName("ROLE_USER"))));
	        }

	        return new org.springframework.security.core.userdetails.User(
	          user.getUsername(), user.getPassword(), user.isEnabled(), true, true, 
	          true, getAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<String> getPrivileges(Collection<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			privileges.add(role.getName());
			Role rolePriv = roleRepository.findByName(role.getName());
			if(rolePriv != null && rolePriv.getPrivileges() != null && !rolePriv.getPrivileges().isEmpty()) {
				collection.addAll(rolePriv.getPrivileges());
			}
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
