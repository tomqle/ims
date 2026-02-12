package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class BatchDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long id;
	public long qty;
	public long qtyAllocated;
	public String location;
	public long productId;
	public long purchaseOrderLineId;
	public List<BatchOrderLineDTO> batchOrderLines;
	
	public BatchDTO() {
		id = 0;
		qty = 0;
		qtyAllocated = 0;
		location = "";
		productId = 0;
		purchaseOrderLineId = 0;
		batchOrderLines = new ArrayList<>();
	}
}
