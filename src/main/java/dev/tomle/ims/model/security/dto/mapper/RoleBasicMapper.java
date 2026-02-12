package dev.tomle.ims.model.security.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.model.dto.mapper.BaseEntityMapper;
import dev.tomle.ims.model.security.Role;
import dev.tomle.ims.model.security.dto.RoleBasicDTO;

@Component
public final class RoleBasicMapper implements BaseEntityMapper<Role, RoleBasicDTO> {

	@Override
	public RoleBasicDTO toDto(Role role) {
		RoleBasicDTO roleDTO = null;
		if(role != null) {
			roleDTO = new RoleBasicDTO();
			setBaseEntityMembers(role, roleDTO);
			roleDTO.setName(role.getName());
		}
		return roleDTO;
	}

	@Override
	public Role toDomain(RoleBasicDTO roleDTO) {
		Role role = null;
		if(roleDTO != null) {
			role = new Role();
			setBaseEntityMembers(roleDTO, role);
			role.setName(roleDTO.getName());
		}
		return role;
	}
}
