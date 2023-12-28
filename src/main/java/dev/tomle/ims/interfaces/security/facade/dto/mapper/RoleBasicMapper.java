package dev.tomle.ims.interfaces.security.facade.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.security.Role;
import dev.tomle.ims.interfaces.security.facade.dto.RoleBasicDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

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
