package dev.tomle.ims.interfaces.product.facade.dto;

import java.io.Serializable;
import java.util.List;

import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class ProductDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long statusId;
	private String sku;
	private String name;

	private List<PurchaseOrderLineDTO> purchaseOrderLines;
	private List<BatchDTO> batches;
	private List<SalesOrderLineDTO> salesOrderLines;
	
	public ProductDTO() {}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PurchaseOrderLineDTO> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}

	public void setPurchaseOrderLines(List<PurchaseOrderLineDTO> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}

	public List<BatchDTO> getBatches() {
		return batches;
	}

	public void setBatches(List<BatchDTO> batches) {
		this.batches = batches;
	}

	public List<SalesOrderLineDTO> getSalesOrderLines() {
		return salesOrderLines;
	}

	public void setSalesOrderLines(List<SalesOrderLineDTO> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}
}

