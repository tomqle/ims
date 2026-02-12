package dev.tomle.ims.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "batchorderline")
public class BatchOrderLine extends BaseEntity {
	
	private long qty;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "batchid")
	private Batch batch;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "salesorderlineid")
	private SalesOrderLine salesOrderLine;
	
	public BatchOrderLine() {}

	public BatchOrderLine(long qty, Batch batch, SalesOrderLine salesOrderLine) {
		this.qty = qty;
		this.batch = batch;
		this.salesOrderLine = salesOrderLine;
	}
	
	public long getQty() {
		return qty;
	}
	
	public void setQty(long qty) {
		this.qty = qty;
	}

	public Batch getBatch() {
		return batch;
	}
	
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	
	public SalesOrderLine getSalesOrderLine() {
		return salesOrderLine;
	}
	
	public void setSalesOrderLine(SalesOrderLine salesOrderLine) {
		this.salesOrderLine = salesOrderLine;
	}
}
