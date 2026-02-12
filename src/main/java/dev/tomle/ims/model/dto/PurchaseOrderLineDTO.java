package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class PurchaseOrderLineDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long lineNumber;
	public String sku;
	public String productName;
	public long qty;
	public long qtyReceived;
	public double cost;
	public long productId;
	public long purchaseOrderId;
	public List<BatchDTO> batches;
	
	public PurchaseOrderLineDTO() {
		this.id = 0;
		this.sku = null;
		this.productName = null;
		this.qty = 0;
		this.qtyReceived = 0;
		this.cost = 0;
		this.productId = 0;
		this.purchaseOrderId = 0;
		batches = null;
	}
	
	public PurchaseOrderLineDTO(long id, long lineNumber, String sku, String productName, long qty, long qtyReceived, double cost, long productId, long purchaseOrderId) {
		this.id = id;
		this.lineNumber = lineNumber;
		this.sku = sku;
		this.productName = productName;
		this.qty = qty;
		this.qtyReceived = qtyReceived;
		this.cost = cost;
		this.productId = productId;
		this.purchaseOrderId = purchaseOrderId;
		this.batches = new ArrayList<>();
	}
}
