package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class SalesOrderLineDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long id;
	public long lineNumber;
	public String sku;
	public String productName;
	public long qty;
	public long qtyAllocated;
	public double cost;
	public double price;
	public double lineTotal;
	public long salesOrderId;
	public long productId;
	public List<BatchOrderLineDTO> batchOrderLines;
	
	public SalesOrderLineDTO() {
		batchOrderLines = new ArrayList<>();
	}
}
