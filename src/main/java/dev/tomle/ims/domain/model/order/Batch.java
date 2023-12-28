package dev.tomle.ims.domain.model.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Batch extends BaseEntity {
	
	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id",
																					"purchaseOrderLine.purchaseOrderId",
																					"product.id",
																					"product.sku",
																					"product.name"));

	private long qty;
	private long qtyAllocated;
	private String location;

	@ManyToOne
	@JoinColumn
	private Product product;
	@ManyToOne
	@JoinColumn
	private PurchaseOrderLine purchaseOrderLine;
	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
	private List<BatchOrderLine> batchOrderLines;
	
	public Batch() {
		batchOrderLines = new ArrayList<>();
	}
	
	public Batch(long qty, String location, Product product) {
		this.qty = qty;
		this.qtyAllocated = 0;
		this.location = location;
		this.product = product;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getQtyAllocated() {
		return qtyAllocated;
	}

	public void setQtyAllocated(long qtyAllocated) {
		this.qtyAllocated = qtyAllocated;
	}
	
	public long getQtyAvailable() {
		return qty - qtyAllocated;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public PurchaseOrderLine getPurchaseOrderLine() {
		return purchaseOrderLine;
	}
	
	public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		this.purchaseOrderLine = purchaseOrderLine;
	}
	
	public List<BatchOrderLine> getBatchOrderLines() {
		return batchOrderLines;
	}
	
	public void setBatchOrderLines(List<BatchOrderLine> batchOrderLines)  {
		this.batchOrderLines = batchOrderLines;
	}
	
	public void addBatchOrderLine(BatchOrderLine batchOrderLine) {
		if(batchOrderLines == null) {
			batchOrderLines = new ArrayList<>();
		}
		batchOrderLines.add(batchOrderLine);
	}
	
	public void validateAllocated() {
		if((batchOrderLines == null && qtyAllocated != 0)
				|| (batchOrderLines != null && batchOrderLines.stream().map(x -> x.getQty()).reduce(0L, Long::sum) != qtyAllocated)) {
		}
	}

	public boolean canAllocate(SalesOrderLine salesOrderLine) {
		if(salesOrderLine.getQtyUnallocated() == 0 || getQtyAvailable() == 0) {
			return false;
		}
		return true;
	}
	
	public void allocate(SalesOrderLine salesOrderLine) {
		validateAllocated();
		salesOrderLine.validateAllocated();
		if(canAllocate(salesOrderLine)) {
			long qtyToAllocate = getQtyAvailable() < salesOrderLine.getQtyUnallocated() ? getQtyAvailable() : salesOrderLine.getQtyUnallocated();
			BatchOrderLine batchOrderLine = new BatchOrderLine(qtyToAllocate, this, salesOrderLine);
			salesOrderLine.setQtyAllocated(salesOrderLine.getQtyAllocated() + qtyToAllocate);
			salesOrderLine.addBatchOrderLine(batchOrderLine);
			qtyAllocated += qtyToAllocate;
			addBatchOrderLine(batchOrderLine);
		}
	}
	
	public void deallocate() {
		
	}
}
