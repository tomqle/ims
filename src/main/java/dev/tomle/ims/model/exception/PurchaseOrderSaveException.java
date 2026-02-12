package dev.tomle.ims.model.exception;

import java.io.Serial;

public class PurchaseOrderSaveException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

	public PurchaseOrderSaveException(Exception e) {
		super(e);
	}

	public PurchaseOrderSaveException() {
		super();
	}
}
