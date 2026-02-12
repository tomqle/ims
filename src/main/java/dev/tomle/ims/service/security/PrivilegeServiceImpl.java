package dev.tomle.ims.service.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.dto.PrivilegeDTO;
import dev.tomle.ims.model.security.dto.mapper.PrivilegeMapper;
import dev.tomle.ims.model.security.repository.PrivilegeRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	private Logger logger = LoggerFactory.getLogger(PrivilegeServiceImpl.class);
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public Page<PrivilegeDTO> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Privilege> privileges = privilegeRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Privilege.SORT_BY));
		Page<PrivilegeDTO> privilegeDTOs = privilegeMapper.toDto(privileges);

		LogUtil.exitMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", privileges);
		return privilegeDTOs;
	}

	@Override
	public Page<PrivilegeDTO> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Privilege> privileges = privilegeRepository.findLikeName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Privilege.SORT_BY));
		Page<PrivilegeDTO> privilegeDTOs = privilegeMapper.toDto(privileges);

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc)", privileges);
		return privilegeDTOs;
	}

	@Override
	public PrivilegeDTO getPrivilegeById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegeById(id)", id);

		Optional<Privilege> privilegeOpt = privilegeRepository.findById(id);
		Privilege privilege = privilegeOpt.isPresent() ? privilegeOpt.get() : null;
		PrivilegeDTO privilegeDTO = privilegeMapper.toDto(privilege);

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegeById(id)", privilege);
		return privilegeDTO;
	}

	@Override
	public PrivilegeDTO savePrivilege(PrivilegeDTO privilege) {
		LogUtil.enterMethod(logger, getClassName(), "savePrivilege(privilege)", privilege);

		Privilege privilegeSaved = privilegeRepository.save(privilegeMapper.toDomain(privilege));
		PrivilegeDTO privilegeSavedDTO = privilegeMapper.toDto(privilegeSaved);

		LogUtil.exitMethod(logger, getClassName(), "savePrivilege(privilege)", privilegeSaved);
		return privilegeSavedDTO;
	}

	@Override
	public void deletePrivilege(PrivilegeDTO privilege) {
		LogUtil.enterMethod(logger, getClassName(), "deletePrivilege(privilege)", privilege);

		privilegeRepository.delete(privilegeMapper.toDomain(privilege));

		LogUtil.exitMethod(logger, getClassName(), "deletePrivilege(privilege)");
	}

	@Override
	public void deletePrivilegeById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePrivilege(id)", id);

		privilegeRepository.deleteById(id);

		LogUtil.exitMethod(logger, getClassName(), "deletePrivilege(id)");
	}
	
	private String getClassName() {
		return PrivilegeServiceImpl.class.getName();
	}
}
