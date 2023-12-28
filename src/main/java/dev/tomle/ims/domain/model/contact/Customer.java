package dev.tomle.ims.domain.model.contact;

import java.util.List;

import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends BaseEntity {

	private long statusId;
	private String firstName;
	private String lastName;
	private String company;
	
	@OneToMany(mappedBy = "customer")
	public List<SalesOrder> salesOrders;

	public Customer() {}

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
	
	public List<SalesOrder> getSalesOrders() {
		return salesOrders;
	}
}
