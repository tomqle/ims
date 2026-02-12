package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public final class ProductDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long statusId;
	public String sku;
	public String name;

	public List<PurchaseOrderLineDTO> purchaseOrderLines;
	public List<BatchDTO> batches;
	public List<SalesOrderLineDTO> salesOrderLines;
	
	public ProductDTO() {}
}

