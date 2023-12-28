package dev.tomle.ims.domain.model.contact;

import java.util.List;

import dev.tomle.ims.domain.model.order.PurchaseOrder;
import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Supplier extends BaseEntity {
	
	private long statusId;
	private String firstName;
	private String lastName;
	private String company;
	
	@OneToMany(mappedBy = "supplier")
	private List<PurchaseOrder> purchaseOrders;
	
	public Supplier() {}

	public Supplier(long statusId, String firstName, String lastName, String company) {
		this.statusId = statusId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
	}
	
	public long getStatusId() {
		return statusId;
	}
	
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
}
