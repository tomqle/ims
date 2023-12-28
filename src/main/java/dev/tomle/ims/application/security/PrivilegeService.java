package dev.tomle.ims.application.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.security.Privilege;

public interface PrivilegeService {
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public Page<Privilege> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public Page<Privilege> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public Privilege getPrivilegeById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_WRITE + "')")
	public Privilege savePrivilege(Privilege privilege);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_DELETE + "')")
	public void deletePrivilege(Privilege privilege);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_DELETE + "')")
	public void deletePrivilegeById(long id);
}
