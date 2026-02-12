package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;

public final class BatchOrderLineDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long id;
	public long qty;
	public long batchId;
	public long salesOrderLineId;
	
	public BatchOrderLineDTO() {}
}
