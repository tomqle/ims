package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class BatchOrderLineDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long qty;
	private long batchId;
	private long salesOrderLineId;
	
	public BatchOrderLineDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public long getSalesOrderLineId() {
		return salesOrderLineId;
	}

	public void setSalesOrderLineId(long salesOrderLineId) {
		this.salesOrderLineId = salesOrderLineId;
	}
	
}
