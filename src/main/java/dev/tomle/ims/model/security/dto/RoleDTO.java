package dev.tomle.ims.model.security.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import dev.tomle.ims.model.dto.BaseEntityDTO;

public class RoleDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
	
	private String name;
	private List<PrivilegeDTO> privileges;

	public RoleDTO() {
		this.name = null;
		this.privileges = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrivilegeDTO> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeDTO> privileges) {
		this.privileges = privileges;
	}
}
