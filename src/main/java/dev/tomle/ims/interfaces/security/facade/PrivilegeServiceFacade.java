package dev.tomle.ims.interfaces.security.facade;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.security.facade.dto.PrivilegeDTO;

public interface PrivilegeServiceFacade {
	public Page<PrivilegeDTO> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<PrivilegeDTO> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name);
	public PrivilegeDTO getPrivilegeById(long id);
	public PrivilegeDTO savePrivilege(PrivilegeDTO privilege);
	public void deletePrivilege(PrivilegeDTO privilege);
	public void deletePrivilegeById(long id);
}
