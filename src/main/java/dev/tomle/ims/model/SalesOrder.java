package dev.tomle.ims.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity(name="salesorder")
public class SalesOrder extends BaseEntity {
	
	public static final List<String> SORT_BY = new ArrayList<>(Arrays.asList("id", "customer.company"));

	@Column(name = "statusid")
	private int statusId;
	private String address1;
	@Column(name = "shippingdescription")
	private String shippingDescription;
	@Column(name = "shippingcost")
	private double shippingCost;
	private double total;
	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;
	@OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
	private List<SalesOrderLine> salesOrderLines;
	
	public SalesOrder() {
		salesOrderLines = new ArrayList<>();
	}

	public SalesOrder(int statusId, String address1, String shippingDescription, double shippingCost, Customer customer, List<SalesOrderLine> salesOrderLines) {
		this.statusId = statusId;
		this.address1 = address1;
		this.shippingDescription = shippingDescription;
		this.shippingCost = shippingCost;
		this.total = 0;
		this.customer = customer;
		this.salesOrderLines = salesOrderLines;
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
	
	public void calculateTotal() {
		this.total = salesOrderLines.stream().map(x -> x.getLineTotal()).reduce(Double::sum).get();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<SalesOrderLine> getSalesOrderLines() {
		return salesOrderLines;
	}
	
	public void addSalesOrderLine(SalesOrderLine salesOrderLine) {
		if(salesOrderLines == null) {
			this.salesOrderLines = new ArrayList<>();
		}
		this.salesOrderLines.add(salesOrderLine);
	}
	
	public void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}
	
	@PrePersist
	public void onPrePersist() {
		calculateTotal();
	}
	
	@PreUpdate
	public void onPreUpdate() {
		calculateTotal();
	}
}
