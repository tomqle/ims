package dev.tomle.ims.model.security.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.model.dto.mapper.BaseEntityMapper;
import dev.tomle.ims.model.security.Privilege;
import dev.tomle.ims.model.security.dto.PrivilegeDTO;

@Component
public final class PrivilegeMapper implements BaseEntityMapper<Privilege, PrivilegeDTO> {

	@Override
	public PrivilegeDTO toDto(Privilege privilege) {
		PrivilegeDTO privilegeDTO = null;
		if(privilege != null) {
			privilegeDTO = new PrivilegeDTO();
			setBaseEntityMembers(privilege, privilegeDTO);
			privilegeDTO.setName(privilege.getName());
		}
		return privilegeDTO;
	}

	@Override
	public Privilege toDomain(PrivilegeDTO privilegeDTO) {
		Privilege privilege = null;
		if(privilegeDTO != null) {
			privilege = new Privilege();
			setBaseEntityMembers(privilegeDTO, privilege);
			privilege.setName(privilegeDTO.getName());
		}
		return privilege;
	}

}
