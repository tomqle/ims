package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class PurchaseOrderLineDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long lineNumber;
	private String sku;
	private String productName;
	private long qty;
	private long qtyReceived;
	private double cost;
	private long productId;
	private long purchaseOrderId;
	private List<BatchDTO> batches;
	
	public PurchaseOrderLineDTO() {
		this.id = 0;
		this.sku = null;
		this.productName = null;
		this.qty = 0;
		this.qtyReceived = 0;
		this.cost = 0;
		this.productId = 0;
		this.purchaseOrderId = 0;
		batches = null;
	}
	
	public PurchaseOrderLineDTO(long id, long lineNumber, String sku, String productName, long qty, long qtyReceived, double cost, long productId, long purchaseOrderId) {
		this.id = id;
		this.lineNumber = lineNumber;
		this.sku = sku;
		this.productName = productName;
		this.qty = qty;
		this.qtyReceived = qtyReceived;
		this.cost = cost;
		this.productId = productId;
		this.purchaseOrderId = purchaseOrderId;
		this.batches = new ArrayList<>();
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

	public long getQtyReceived() {
		return qtyReceived;
	}
	
	public void setQtyReceived(long qtyReceived) {
		this.qtyReceived = qtyReceived;
	}

	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}

	public long getProductId() {
		return productId;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	
	public void setPurchaseOrderId(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	public List<BatchDTO> getBatches() {
		return batches;
	}
	
	public void addBatch(BatchDTO batch) {
		if(batches == null) {
			batches = new ArrayList<>();
		}
		batches.add(batch);
	}
	
	public void setBatches(List<BatchDTO> batches) {
		this.batches = batches;
	}
}
