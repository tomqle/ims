package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class PurchaseOrderDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address1;
	private String shippingDescription;
	private double shippingCost;
	private double total;
	private long supplierId;
	private List<PurchaseOrderLineDTO> purchaseOrderLines;
	
	public PurchaseOrderDTO() {
		purchaseOrderLines = new ArrayList<>();
	}

	public PurchaseOrderDTO(long id, String address1, String shippingDescription, double shippingCost, double total, long supplierId, List<PurchaseOrderLineDTO> purchaseOrderLines) {
		this.id = id;
		this.address1 = address1;
		this.shippingDescription = shippingDescription;
		this.shippingCost = shippingCost;
		this.total = total;
		this.supplierId = supplierId;
		this.purchaseOrderLines = purchaseOrderLines;
	}
	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getShippingDescription() {
		return shippingDescription;
	}
	
	public void setShippingDescription(String shippingDescription) {
		this.shippingDescription = shippingDescription;
	}
	
	public double getShippingCost() {
		return shippingCost;
	}
	
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public long getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	
	public List<PurchaseOrderLineDTO> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}
	
	public void addPurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine) {
		if(purchaseOrderLines == null) {
			purchaseOrderLines = new ArrayList<>();
		}
		purchaseOrderLines.add(purchaseOrderLine);
	}
	
	public void setPurchaseOrderLines(List<PurchaseOrderLineDTO> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}
}
