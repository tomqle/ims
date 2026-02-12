package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class SalesOrderDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

	public long id;
	public int statusId;
	public String address1;
	public String shippingDescription;
	public double shippingCost;
	public double total;
	public long customerId;
	public List<SalesOrderLineDTO> salesOrderLines;
	
	public SalesOrderDTO() {
		salesOrderLines = new ArrayList<>();
	}
}
