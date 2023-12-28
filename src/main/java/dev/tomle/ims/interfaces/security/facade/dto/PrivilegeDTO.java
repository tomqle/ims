package dev.tomle.ims.interfaces.security.facade.dto;

import java.io.Serializable;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public class PrivilegeDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;

	public PrivilegeDTO() {
		this.id = 0;
		this.setName(null);
	}
	
	public PrivilegeDTO(long id, String name) {
		this.id = id;
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
