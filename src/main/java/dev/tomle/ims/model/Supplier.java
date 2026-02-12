package dev.tomle.ims.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Supplier extends BaseEntity {
	
	@Column(name = "statusid")
	private long statusId;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
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
