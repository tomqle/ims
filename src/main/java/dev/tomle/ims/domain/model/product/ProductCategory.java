package dev.tomle.ims.domain.model.product;

import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class ProductCategory extends BaseEntity {
	
	private String name;
	
	public ProductCategory() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
