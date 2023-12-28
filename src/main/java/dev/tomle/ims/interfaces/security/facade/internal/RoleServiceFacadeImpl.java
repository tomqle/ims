package dev.tomle.ims.interfaces.security.facade.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.security.RoleService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.security.Role;
import dev.tomle.ims.interfaces.security.facade.RoleServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.RoleDTO;
import dev.tomle.ims.interfaces.security.facade.dto.mapper.RoleMapper;

@Service
public class RoleServiceFacadeImpl implements RoleServiceFacade {

	private Logger logger = LoggerFactory.getLogger(RoleServiceFacadeImpl.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Page<RoleDTO> getRoles(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Role> roles = roleService.getRoles(pageNum, pageSize, sortBy, desc);
		Page<RoleDTO> roleDTOs = roleMapper.toDto(roles);

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc)", roleDTOs);
		return roleDTOs;
	}

	@Override
	public Page<RoleDTO> getRolesByName(int pageNum, int pageSize, String sortBy, boolean desc, String name) {
		LogUtil.enterMethod(logger, getClassName(), "getRolesByName(pageNum, pageSize, sortBy, desc, name)", pageNum, pageSize, sortBy, desc, name);

		Page<Role> roles = roleService.getRolesByName(pageNum, pageSize, sortBy, desc, name);
		Page<RoleDTO> roleDTOs = roleMapper.toDto(roles);

		LogUtil.exitMethod(logger, getClassName(), "getRoles(pageNum, pageSize, sortBy, desc, name)", roleDTOs);
		return roleDTOs;
	}

	@Override
	public RoleDTO getRoleById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getRoleById(id)", id);

		RoleDTO roleDTO = roleMapper.toDto(roleService.getRoleById(id));

		LogUtil.exitMethod(logger, getClassName(), "getRoleById(id)", roleDTO);
		return roleDTO;
	}

	@Override
	public RoleDTO saveRole(RoleDTO role) {
		LogUtil.enterMethod(logger, getClassName(), "saveRole(roleDTO)", role);

		RoleDTO roleSaved = roleMapper.toDto(roleService.saveRole(roleMapper.toDomain(role)));
		
		LogUtil.exitMethod(logger, getClassName(), "saveRole(roleDTO)", roleSaved);
		return roleSaved;
	}

	@Override
	public void deleteRole(RoleDTO role) {
		LogUtil.enterMethod(logger, getClassName(), "deleteRole(roleDTO)", role);
		roleService.deleteRole(roleMapper.toDomain(role));
		LogUtil.exitMethod(logger, getClassName(), "deleteRole(roleDTO)");
	}

	@Override
	public void deleteRoleById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteRoleById(id)", id);
		roleService.deleteRoleById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteRoleById(id)");
	}

	private String getClassName() {
		return RoleServiceFacadeImpl.class.getName();
	}
}
