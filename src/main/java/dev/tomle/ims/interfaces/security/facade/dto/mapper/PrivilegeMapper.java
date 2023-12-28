package dev.tomle.ims.interfaces.security.facade.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.security.Privilege;
import dev.tomle.ims.interfaces.security.facade.dto.PrivilegeDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

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
