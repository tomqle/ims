package dev.tomle.ims.model;

import jakarta.persistence.Entity;

@Entity(name = "orderstatus")
public class OrderStatus extends BaseEntity {

	private String status;

	public enum StatusEnum {
		PENDING(1),
		NEW(2),
		PROCESSING(3),
		AWAITING_PAYMENT(4),
		//AWAITING_FULFILLMENT(),
		//AWAITING_SHIPMENT(),
		//AWAITING_PICKUP(),
		//AWAITING_RECEIPT(),
		PARTIALLY_SHIPPED(5),
		FULLY_SHIPPED(6),
		CANCELLED(7),
		//DECLINED(),
		//REFUNDED()
		COMPLETED(8)
		;
		
		public final int id;
		
		private StatusEnum(int id) {
			this.id = id;
		}
	}
	
	public OrderStatus() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
