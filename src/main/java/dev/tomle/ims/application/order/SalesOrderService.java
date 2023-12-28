package dev.tomle.ims.application.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.domain.model.order.SalesOrderLine;
import dev.tomle.ims.domain.model.security.Privilege;


public interface SalesOrderService {
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public List<SalesOrder> getSalesOrders();
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public Page<SalesOrder> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public SalesOrder getSalesOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public SalesOrder saveSalesOrder(SalesOrder salesOrder);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public SalesOrderLine getSalesOrderLineById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public SalesOrderLine saveSalesOrderLine(SalesOrderLine salesOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_ALLOCATE + "')")
	public SalesOrderLine allocateSalesOrderLine(long salesOrderLineId, long batchId, long qty);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_DELETE + "')")
	public void deleteSalesOrder(SalesOrder salesOrder);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_DELETE + "')")
	public void deleteSalesOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public void deleteSalesOrderLine(SalesOrderLine salesOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public void deleteSalesOrderLineById(long id);
}
