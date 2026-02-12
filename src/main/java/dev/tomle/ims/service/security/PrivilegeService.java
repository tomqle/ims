package dev.tomle.ims.service.security;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.dto.PrivilegeDTO;

public interface PrivilegeService {
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public Page<PrivilegeDTO> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public Page<PrivilegeDTO> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_READ + "')")
	public PrivilegeDTO getPrivilegeById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_WRITE + "')")
	public PrivilegeDTO savePrivilege(PrivilegeDTO privilege);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_DELETE + "')")
	public void deletePrivilege(PrivilegeDTO privilege);
	@PreAuthorize("hasAuthority('" + Privilege.PRIVILEGE_DELETE + "')")
	public void deletePrivilegeById(long id);
}
