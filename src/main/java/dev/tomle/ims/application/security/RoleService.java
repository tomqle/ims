package dev.tomle.ims.application.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.domain.model.security.Role;

public interface RoleService {
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public Page<Role> getRoles(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public Page<Role> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public Role getRoleById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_WRITE + "')")
	public Role saveRole(Role role);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_DELETE + "')")
	public void deleteRole(Role role);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_DELETE + "')")
	public void deleteRoleById(long id);
}
