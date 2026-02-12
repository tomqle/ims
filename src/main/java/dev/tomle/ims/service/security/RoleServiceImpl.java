package dev.tomle.ims.service.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.security.Role;
import dev.tomle.ims.model.security.dto.RoleDTO;
import dev.tomle.ims.model.security.dto.mapper.RoleMapper;
import dev.tomle.ims.model.security.repository.RoleRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class RoleServiceImpl implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Page<RoleDTO> getRoles(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Role> roles = roleRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Role.SORT_BY));
		Page<RoleDTO> roleDTOs = roleMapper.toDto(roles);

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", roles);
		return roleDTOs;
	}

	@Override
	public Page<RoleDTO> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getRolesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Role> roles = roleRepository.findLikeName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Role.SORT_BY));
		Page<RoleDTO> roleDTOs = roleMapper.toDto(roles);

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc, name)", roles);
		return roleDTOs;
	}

	@Override
	public RoleDTO getRoleById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getRoleById(id)", id);

		Optional<Role> roleOpt = roleRepository.findById(id);
		Role role = roleOpt.isPresent() ? roleOpt.get() : null;
		RoleDTO roleDTO = roleMapper.toDto(role);

		LogUtil.exitMethod(logger, getClassName(), "getRoleById(id)", role);
		return roleDTO;
	}

	@Override
	public RoleDTO saveRole(RoleDTO role) {
		LogUtil.enterMethod(logger, getClassName(), "saveRole(role)", role);

		Role roleSaved = roleRepository.save(roleMapper.toDomain(role));
		RoleDTO roleSavedDTO = roleMapper.toDto(roleSaved);

		LogUtil.exitMethod(logger, getClassName(), "saveRole(id)", roleSaved);
		return roleSavedDTO;
	}

	@Override
	public void deleteRole(RoleDTO role) {
		LogUtil.enterMethod(logger, getClassName(), "deleteRole(role)", role);
		roleRepository.delete(roleMapper.toDomain(role));
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
