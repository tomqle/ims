package dev.tomle.ims.interfaces.order.facade;

import java.util.List;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;

public interface SalesOrderServiceFacade {

	public List<SalesOrderDTO> getSalesOrders();
	public Page<SalesOrderDTO> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	public SalesOrderDTO getSalesOrderById(long id);
	public SalesOrderDTO saveSalesOrder(SalesOrderDTO salesOrderDTO);
	public SalesOrderLineDTO getSalesOrderLineById(long id);
	public SalesOrderLineDTO saveSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO);
	public SalesOrderLineDTO allocateSalesOrderLine(long salesOrderLineId, BatchDTO batchDTO);
	public void deleteSalesOrder(SalesOrderDTO salesOrder);
	public void deleteSalesOrderById(long id);
	public void deleteSalesOrderLine(SalesOrderLineDTO salesOrderLine);
	public void deleteSalesOrderLineById(long id);
}
