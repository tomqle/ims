package dev.tomle.ims.application.security.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.RoleService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.security.Role;
import dev.tomle.ims.infrastructure.security.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<Role> getRoles(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Role> roles = roleRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Role.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", roles);
		return roles;
	}

	@Override
	public Page<Role> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getRolesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Role> roles = roleRepository.findLikeName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Role.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc, name)", roles);
		return roles;
	}

	@Override
	public Role getRoleById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getRoleById(id)", id);

		Optional<Role> roleOpt = roleRepository.findById(id);
		Role role = roleOpt.isPresent() ? roleOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getRoleById(id)", role);
		return role;
	}

	@Override
	public Role saveRole(Role role) {
		LogUtil.enterMethod(logger, getClassName(), "saveRole(role)", role);

		Role roleSaved = roleRepository.save(role);

		LogUtil.exitMethod(logger, getClassName(), "saveRole(id)", roleSaved);
		return roleSaved;
	}

	@Override
	public void deleteRole(Role role) {
		LogUtil.enterMethod(logger, getClassName(), "deleteRole(role)", role);
		roleRepository.delete(role);
		LogUtil.exitMethod(logger, getClassName(), "deleteRole(role)");
	}

	@Override
	public void deleteRoleById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteRoleById(id)", id);
		roleRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteRoleId(id)");
	}
	
	private String getClassName() {
		return RoleServiceImpl.class.getName();
	}
}
