package dev.tomle.ims.model;

public class PurchaseOrderStatus extends BaseEntity {

	private String status;

	public enum StatusEnum {
		PENDING(1),
		NEW(2),
		PROCESSING(3),
		AWAITING_PAYMENT(4),
		IN_TRANSIT(5),
		AWAITING_RECEIPT(6),
		PARTIALLY_RECEIVED(7),
		FULLY_RECEIVED(8),
		CANCELLED(9),
		COMPLETED(10)
		;
		
		public final int id;
		
		private StatusEnum(int id) {
			this.id = id;
		}
	}
	
	public PurchaseOrderStatus() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
