package dev.tomle.ims.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity(name="productstatus")
public class ProductStatus extends BaseEntity {

	private String status;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="id")
	private List<Product> products;
	
	public ProductStatus() {
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public enum StatusType {
		ACTIVE(1),
		INACTIVE(2);
		
		public final int id;
		
		private StatusType(int id) {
			this.id = id;
		}
	}
}
