package dev.tomle.ims.model.exception;

import java.io.Serial;

import dev.tomle.ims.model.PurchaseOrderLine;

public class OverReceiveException extends PurchaseOrderSaveException {

    @Serial
    private static final long serialVersionUID = 1L;
	private PurchaseOrderLine purchaseOrderLine;

	public OverReceiveException(PurchaseOrderLine purchaseOrderLine) {
		this.purchaseOrderLine = purchaseOrderLine;
	}
	
	@Override
	public String getMessage() {
		String purchaseOrderId = purchaseOrderLine.getPurchaseOrder() == null ? "" : String.valueOf(purchaseOrderLine.getPurchaseOrder().getId());
		return "PO " + purchaseOrderId
				+ "line " + purchaseOrderLine.getLineNumber()
				+ ": Cannot receive more quantity than ordered"
				+ "(expected: " + purchaseOrderLine.getQty()
				+ ", actual: " + purchaseOrderLine.getQtyReceived() + ").";
	}
}
