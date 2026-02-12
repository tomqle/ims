package dev.tomle.ims.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Product extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "sku", "name", "statusId", "lastModifiedDate", "lastModifiedBy"));

	private String sku;
	private String name;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	//private ProductStatus status;
	@Column(name="statusid")
	private long statusId;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "statusid", referencedColumnName = "id")
	private ProductStatus status;*/
	
	@OneToMany(mappedBy = "product")
	private List<PurchaseOrderLine> purchaseOrderLines;
	@OneToMany(mappedBy = "product")
	private List<Batch> batches;
	@OneToMany(mappedBy = "product")
	private List<SalesOrderLine> salesOrderLines;
	
	public Product() { }
	
	public Product(long id, String sku, String name, long statusId) {
		this(sku, name, statusId);
		this.id = id;
	}
	
	public Product(String sku, String name, long statusId) {
		this.sku = sku;
		this.name = name;
		this.statusId = statusId;
	}

	public long getId() {
		return id;
	}
	
	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getProductStatusId() {
		return statusId;
	}
	
	public void setProductStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	public List<PurchaseOrderLine> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}
	
	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}
	
	public List<Batch> getBatches() {
		return batches;
	}
	
	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	
	public List<SalesOrderLine> getSalesOrderLines() {
		return salesOrderLines;
	}

	public void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}
}
