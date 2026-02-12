package dev.tomle.ims.model.security.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.dto.mapper.BaseEntityMapper;
import dev.tomle.ims.model.security.Role;
import dev.tomle.ims.model.security.dto.RoleDTO;

@Component
public final class RoleMapper implements BaseEntityMapper<Role, RoleDTO> {
	
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public RoleDTO toDto(Role role) {
		RoleDTO roleDTO = null;
		if(role != null) {
			roleDTO = new RoleDTO();
			setBaseEntityMembers(role, roleDTO);
			roleDTO.setName(role.getName());
			roleDTO.setPrivileges(privilegeMapper.toDto(role.getPrivileges()));
		}
		return roleDTO;
	}

	@Override
	public Role toDomain(RoleDTO roleDTO) {
		Role role = null;
		if(roleDTO != null) {
			role = new Role();
			setBaseEntityMembers(roleDTO, role);
			role.setName(roleDTO.getName());
			role.setPrivileges(privilegeMapper.toDomain(roleDTO.getPrivileges()));
		}

		return role;
	}

}
