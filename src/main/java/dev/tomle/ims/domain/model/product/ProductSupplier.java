package dev.tomle.ims.domain.model.product;

import dev.tomle.ims.domain.model.contact.Supplier;
import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductSupplier extends BaseEntity {
	
	private double cost;
	private long leadTime; // days
	private long minQty;
	private boolean isPrimary;
	
	@ManyToOne
	private Product product;
	@ManyToOne
	private Supplier supplier;
	
	public ProductSupplier() {}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public long getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(long leadTime) {
		this.leadTime = leadTime;
	}

	public long getMinQty() {
		return minQty;
	}

	public void setMinQty(long minQty) {
		this.minQty = minQty;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
