package dev.tomle.ims.interfaces.product.facade.dto;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public class ProductStatusDTO extends BaseEntityDTO {

	private String status;
	
	public ProductStatusDTO() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
