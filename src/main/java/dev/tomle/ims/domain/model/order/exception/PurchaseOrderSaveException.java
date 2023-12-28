package dev.tomle.ims.domain.model.order.exception;

public class PurchaseOrderSaveException extends Exception {
	private static final long serialVersionUID = 1L;

	public PurchaseOrderSaveException(Exception e) {
		super(e);
	}

	public PurchaseOrderSaveException() {
		super();
	}
}
