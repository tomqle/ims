package dev.tomle.ims.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity(name = "purchaseorder")
public class PurchaseOrder extends BaseEntity {

	@Transient
	public static final List<String> SORT_BY = new ArrayList<String>(Arrays.asList("id", "supplier.company"));

	@Column(name = "statusid")
	private int statusId;
	private String address1;
	@Column(name = "shippingdescription")
	private String shippingDescription;
	@Column(name = "shippingcost")
	private double shippingCost;
	private double total;
	@ManyToOne
	@JoinColumn(name = "supplierid")
	private Supplier supplier;
	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	private List<PurchaseOrderLine> purchaseOrderLines;
	
	public PurchaseOrder() {
		statusId = 0;
		address1 = null;
		shippingDescription = null;
		shippingCost = 0.0;
		total = 0.0;
		supplier = null;
		purchaseOrderLines = new ArrayList<>();
	}

	public PurchaseOrder(long id, String address1, String shippingDescription, double shippingCost, double total, Supplier supplier, List<PurchaseOrderLine> purchaseOrderLines) {
		this(address1, shippingDescription, shippingCost, total, supplier, purchaseOrderLines);
		this.id = id;
	}

	public PurchaseOrder(String address1, String shippingDescription, double shippingCost, double total, Supplier supplier, List<PurchaseOrderLine> purchaseOrderLines) {
		this.address1 = address1;
		this.shippingDescription = shippingDescription;
		this.shippingCost = shippingCost;
		this.total = total;
		this.supplier = supplier;
		this.purchaseOrderLines = purchaseOrderLines;
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
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public List<PurchaseOrderLine> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}
	
	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}
	
	public boolean addPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		if(purchaseOrderLines == null) {
			purchaseOrderLines = new ArrayList<>();
		}
		return this.purchaseOrderLines.add(purchaseOrderLine);
	}
	
	public double calculateTotal() {
		double sum = 0;
		
		for(PurchaseOrderLine line: purchaseOrderLines) {
			sum += line.getCost() * line.getQty();
		}
		sum += shippingCost;
		total = sum;
		return total;
	}

	public void receive() throws PurchaseOrderSaveException {
		boolean isFullyReceived = true;
		for(PurchaseOrderLine line: purchaseOrderLines) {
			//line.receive();
			if(!line.isFullyReceived()) {
				isFullyReceived = false;
			}
		}
		statusId = isFullyReceived ? PurchaseOrderStatus.StatusEnum.FULLY_RECEIVED.id : PurchaseOrderStatus.StatusEnum.PARTIALLY_RECEIVED.id;
	}
	
	@PrePersist
	public void onPrePersist() {
		calculateTotal();
	}
	
	@PreUpdate
	public void onPreUpdate() {
		calculateTotal();
	}
	
	/*@PreRemove
	public void onPreRemove() {
	}*/
}
