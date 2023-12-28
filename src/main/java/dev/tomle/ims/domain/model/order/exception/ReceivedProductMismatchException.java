package dev.tomle.ims.domain.model.order.exception;

import dev.tomle.ims.domain.model.product.Product;

public class ReceivedProductMismatchException extends PurchaseOrderSaveException {

	private static final long serialVersionUID = 1L;
	private Product purchaseOrderLineProduct;
	private Product batchProduct;

	public ReceivedProductMismatchException(Product purchaseOrderLineProduct, Product batchProduct) {
		this.purchaseOrderLineProduct = purchaseOrderLineProduct;
		this.batchProduct = batchProduct;
	}
	
	@Override
	public String getMessage() {
		return "Batch has " + purchaseOrderLineProduct == null ? "null product" : "product id " + purchaseOrderLineProduct.getId()
				+ "; line item has " + batchProduct == null ? "null product" : "product id " + batchProduct.getId();
	}
}
