package dev.tomle.ims.application.security.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.PrivilegeService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.infrastructure.security.repository.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	private Logger logger = LoggerFactory.getLogger(PrivilegeServiceImpl.class);
	
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public Page<Privilege> getPrivileges(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Privilege> privileges = privilegeRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Privilege.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getPrivileges(pageNum, pageSize, sortBy, desc)", privileges);
		return privileges;
	}

	@Override
	public Page<Privilege> getPrivilegesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Privilege> privileges = privilegeRepository.findLikeName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Privilege.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegesByName(pageNum, pageSize, sortBy, desc)", privileges);
		return privileges;
	}

	@Override
	public Privilege getPrivilegeById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPrivilegeById(id)", id);

		Optional<Privilege> privilegeOpt = privilegeRepository.findById(id);
		Privilege privilege = privilegeOpt.isPresent() ? privilegeOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getPrivilegeById(id)", privilege);
		return privilege;
	}

	@Override
	public Privilege savePrivilege(Privilege privilege) {
		LogUtil.enterMethod(logger, getClassName(), "savePrivilege(privilege)", privilege);

		Privilege privilegeSaved = privilegeRepository.save(privilege);

		LogUtil.exitMethod(logger, getClassName(), "savePrivilege(privilege)", privilegeSaved);
		return privilegeSaved;
	}

	@Override
	public void deletePrivilege(Privilege privilege) {
		LogUtil.enterMethod(logger, getClassName(), "deletePrivilege(privilege)", privilege);

		privilegeRepository.delete(privilege);

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
