package dev.tomle.ims.interfaces.order.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.tomle.ims.interfaces.shared.dto.BaseEntityDTO;

public final class SalesOrderDTO extends BaseEntityDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private int statusId;
	private String address1;
	private String shippingDescription;
	private double shippingCost;
	private double total;
	private long customerId;
	private List<SalesOrderLineDTO> salesOrderLines;
	
	public SalesOrderDTO() {
		salesOrderLines = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public List<SalesOrderLineDTO> getSalesOrderLines() {
		return salesOrderLines;
	}
	
	public void addSalesOrderLine(SalesOrderLineDTO salesOrderLine) {
		if(salesOrderLines == null) {
			salesOrderLines = new ArrayList<>();
		}
		salesOrderLines.add(salesOrderLine);
	}
	
	public void setSalesOrderLines(List<SalesOrderLineDTO> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}
}
