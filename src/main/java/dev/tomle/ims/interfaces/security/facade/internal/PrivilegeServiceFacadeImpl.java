package dev.tomle.ims.interfaces.security.facade.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.PrivilegeService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.interfaces.security.facade.PrivilegeServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.PrivilegeDTO;
import dev.tomle.ims.interfaces.security.facade.dto.mapper.PrivilegeMapper;

@Service
public class PrivilegeServiceFacadeImpl implements PrivilegeServiceFacade {
	
	private Logger logger = LoggerFactory.getLogger(PrivilegeServiceFacadeImpl.class);
	
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public Page<PrivilegeDTO> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Privilege> privileges = privilegeService.getPrivileges(pageNum, pageSize, sortBy, desc);
		Page<PrivilegeDTO> privilegeDTOs = privilegeMapper.toDto(privileges);

		LogUtil.exitMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", privilegeDTOs);
		return privilegeDTOs;
	}

	@Override
	public Page<PrivilegeDTO> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Privilege> privileges = privilegeService.getPrivilegesByName(pageNum, pageSize, sortBy, desc, name);
		Page<PrivilegeDTO> privilegeDTOs = privilegeMapper.toDto(privileges);

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc, name)", privilegeDTOs);
		return privilegeDTOs;
	}

	@Override
	public PrivilegeDTO getPrivilegeById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegeById(pageNum, pageSize, sortBy, desc, name)", id);

		PrivilegeDTO privilegeDTO = privilegeMapper.toDto(privilegeService.getPrivilegeById(id));

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegeById(pageNum, pageSize, sortBy, desc, name)", privilegeDTO);
		return privilegeDTO;
	}

	@Override
	public PrivilegeDTO savePrivilege(PrivilegeDTO privilege) {
		LogUtil.enterMethod(logger, getClassName(), "savePrivilege(privilege)", privilege);

		PrivilegeDTO privilegeSaved = privilegeMapper.toDto(privilegeService.savePrivilege(privilegeMapper.toDomain(privilege)));

		LogUtil.exitMethod(logger, getClassName(), "savePrivilege(privilege)", privilegeSaved);
		return privilegeSaved;
	}

	@Override
	public void deletePrivilege(PrivilegeDTO privilege) {
		LogUtil.enterMethod(logger, getClassName(), "deletePrivilege(privilege)", privilege);
		privilegeService.deletePrivilege(privilegeMapper.toDomain(privilege));
		LogUtil.exitMethod(logger, getClassName(), "deletePrivilege(privilege)");
	}

	@Override
	public void deletePrivilegeById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePrivilegeById(id)", id);
		privilegeService.deletePrivilegeById(id);
		LogUtil.exitMethod(logger, getClassName(), "deletePrivilegeById(id)");
	}

	private String getClassName() {
		return PrivilegeServiceFacadeImpl.class.getName();
	}
}
