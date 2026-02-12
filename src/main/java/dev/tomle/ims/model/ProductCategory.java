package dev.tomle.ims.model;

import jakarta.persistence.Entity;

@Entity(name = "productcategory")
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
