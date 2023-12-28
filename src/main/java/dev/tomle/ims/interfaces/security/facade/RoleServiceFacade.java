package dev.tomle.ims.interfaces.security.facade;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.security.facade.dto.RoleDTO;

public interface RoleServiceFacade {
	public Page<RoleDTO> getRoles(int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<RoleDTO> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	public RoleDTO getRoleById(long id);
	public RoleDTO saveRole(RoleDTO role);
	public void deleteRole(RoleDTO role);
	public void deleteRoleById(long id);
}
