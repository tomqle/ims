package dev.tomle.ims.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.SalesOrderDTO;
import dev.tomle.ims.model.dto.SalesOrderLineDTO;
import dev.tomle.ims.model.security.Privilege;


public interface SalesOrderService {
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public List<SalesOrderDTO> getSalesOrders();
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public Page<SalesOrderDTO> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public SalesOrderDTO getSalesOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public SalesOrderDTO saveSalesOrder(SalesOrderDTO salesOrder);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_READ + "')")
	public SalesOrderLineDTO getSalesOrderLineById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public SalesOrderLineDTO saveSalesOrderLine(SalesOrderLineDTO salesOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_ALLOCATE + "')")
	public SalesOrderLineDTO allocateSalesOrderLine(long salesOrderLineId, BatchDTO batchDTO);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_DELETE + "')")
	public void deleteSalesOrder(SalesOrderDTO salesOrder);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_DELETE + "')")
	public void deleteSalesOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public void deleteSalesOrderLine(SalesOrderLineDTO salesOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.SALES_ORDER_WRITE + "')")
	public void deleteSalesOrderLineById(long id);
}
