package dev.tomle.ims.service.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.dto.RoleDTO;

public interface RoleService {
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public Page<RoleDTO> getRoles(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public Page<RoleDTO> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_READ + "')")
	public RoleDTO getRoleById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_WRITE + "')")
	public RoleDTO saveRole(RoleDTO role);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_DELETE + "')")
	public void deleteRole(RoleDTO role);
	@PreAuthorize("hasAuthority('" + Privilege.ROLE_DELETE + "')")
	public void deleteRoleById(long id);
}
