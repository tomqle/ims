package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class SalesOrderLineDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long lineNumber;
	private String sku;
	private String productName;
	private long qty;
	private long qtyAllocated;
	private double cost;
	private double price;
	private double lineTotal;
	private long salesOrderId;
	private long productId;
	private List<BatchOrderLineDTO> batchOrderLines;
	
	public SalesOrderLineDTO() {
		batchOrderLines = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public List<BatchOrderLineDTO> getBatchOrderLines() {
		return batchOrderLines;
	}

	public void addBatchOrderLine(BatchOrderLineDTO batchOrderLine) {
		if(batchOrderLines == null) {
			batchOrderLines = new ArrayList<>();
		}
		batchOrderLines.add(batchOrderLine);
	}
	
	public void setBatchOrderLines(List<BatchOrderLineDTO> batchOrderLines) {
		this.batchOrderLines = batchOrderLines;
	}
}
