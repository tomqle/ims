package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class PurchaseOrderDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public String address1;
	public String shippingDescription;
	public double shippingCost;
	public double total;
	public long supplierId;
	public List<PurchaseOrderLineDTO> purchaseOrderLines;
	
	public PurchaseOrderDTO() {
		purchaseOrderLines = new ArrayList<>();
	}

	public PurchaseOrderDTO(long id, String address1, String shippingDescription, double shippingCost, double total, long supplierId, List<PurchaseOrderLineDTO> purchaseOrderLines) {
		this.id = id;
		this.address1 = address1;
		this.shippingDescription = shippingDescription;
		this.shippingCost = shippingCost;
		this.total = total;
		this.supplierId = supplierId;
		this.purchaseOrderLines = purchaseOrderLines;
	}
}
