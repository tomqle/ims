package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class BatchDTO extends BaseEntityDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private long qty;
	private long qtyAllocated;
	private String location;
	private long productId;
	private long purchaseOrderLineId;
	private List<BatchOrderLineDTO> batchOrderLines;
	
	public BatchDTO() {
		id = 0;
		qty = 0;
		qtyAllocated = 0;
		location = "";
		productId = 0;
		purchaseOrderLineId = 0;
		batchOrderLines = new ArrayList<>();
	}

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

	public long getQtyAllocated() {
		return qtyAllocated;
	}
	
	public void setQtyAllocated(long qtyAllocated) {
		this.qtyAllocated = qtyAllocated;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public long getProductId() {
		return productId;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getPurchaseOrderLineId() {
		return purchaseOrderLineId;
	}
	
	public void setPurchaseOrderLineId(long purchaseOrderLineId) {
		this.purchaseOrderLineId = purchaseOrderLineId;
	}

	public List<BatchOrderLineDTO> getBatchOrderLines() {
		return batchOrderLines;
	}

	public void addBatchOrderLine(BatchOrderLineDTO batchOrderLineDTO) {
		if(batchOrderLines == null) {
			batchOrderLines = new ArrayList<>();
		}
		this.batchOrderLines.add(batchOrderLineDTO);
	}
	
	public void setBatchOrderLines(List<BatchOrderLineDTO> batchOrderLines) {
		this.batchOrderLines = batchOrderLines;
	}
}
