package dev.tomle.ims.domain.model.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class SalesOrderLine extends BaseEntity {
	@Transient
	protected Logger logger = LoggerFactory.getLogger(BaseEntity.class);

	private long lineNumber;
	private String sku;
	private String productName;
	private long qty;
	private long qtyAllocated;
	private double cost;
	private double price;
	private double lineTotal;
	
	@ManyToOne
	@JoinColumn
	private SalesOrder salesOrder;
	@ManyToOne
	@JoinColumn
	private Product product;
	@OneToMany(mappedBy = "salesOrderLine", cascade = CascadeType.ALL)
	private List<BatchOrderLine> batchOrderLines;
	
	public SalesOrderLine() {
		batchOrderLines = new ArrayList<>();
	}
	
	public SalesOrderLine(long lineNumber, String sku, String productName, long qty, long qtyAllocated, Product product, SalesOrder salesOrder) {
		this.lineNumber = lineNumber;
		this.sku = sku;
		this.productName = productName;
		this.qty = qty;
		this.qtyAllocated = qtyAllocated;
		this.product = product;
		this.salesOrder = salesOrder;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getQtyAllocated() {
		return qtyAllocated;
	}

	public void setQtyAllocated(long qtyAllocated) {
		this.qtyAllocated = qtyAllocated;
	}

	public long getQtyUnallocated() {
		return qty - qtyAllocated;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	public void calcLineTotal() {
		lineTotal = price * qty;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}
	
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
	
	public List<BatchOrderLine> getBatchOrderLines() {
		return batchOrderLines;
	}
	
	public void addBatchOrderLine(BatchOrderLine batchOrderLine) {
		if(batchOrderLines == null) {
			batchOrderLines = new ArrayList<>();
		}
		this.batchOrderLines.add(batchOrderLine);
	}
	
	public void setBatchOrderLines(List<BatchOrderLine> batchOrderLines) {
		this.batchOrderLines = batchOrderLines;
	}

	public void validateAllocated() {
		if((batchOrderLines == null && qtyAllocated != 0)
				|| (batchOrderLines != null && batchOrderLines.stream().map(x -> x.getQty()).reduce(0L, Long::sum) != qtyAllocated)) {
		}
	}

	public boolean canAllocate(Batch batch) {
		if(getQtyUnallocated() == 0 || batch.getQtyAvailable() == 0) {
			return false;
		}
		return true;
	}
	
	public void allocate(Batch batch) {
		logger.debug("Entering allocate(): parameters " + batch);
		validateAllocated();
		batch.validateAllocated();
		if(canAllocate(batch)) {
			long qtyToAllocate = batch.getQtyAvailable() < getQtyUnallocated() ? batch.getQtyAvailable() : getQtyUnallocated();
			BatchOrderLine batchOrderLine = new BatchOrderLine(qtyToAllocate, batch, this);
			batch.setQtyAllocated(batch.getQtyAllocated() + qtyToAllocate);
			batch.addBatchOrderLine(batchOrderLine);
			setQtyAllocated(getQtyAllocated() + qtyToAllocate);
			addBatchOrderLine(batchOrderLine);
		}
		logger.debug("Exiting allocate(): return void");
	}
	
	public void deallocate() {
		
	}
}
