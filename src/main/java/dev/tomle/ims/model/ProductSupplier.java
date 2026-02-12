package dev.tomle.ims.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="productsupplier")
public class ProductSupplier extends BaseEntity {
	
	private double cost;
	@Column(name = "leadtime")
	private long leadTime; // days
	@Column(name = "minqty")
	private long minQty;
	@Column(name = "isprimary")
	private boolean isPrimary;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "supplierid")
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
