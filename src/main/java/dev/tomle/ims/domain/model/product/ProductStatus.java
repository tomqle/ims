package dev.tomle.ims.domain.model.product;

import java.util.List;

import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
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
