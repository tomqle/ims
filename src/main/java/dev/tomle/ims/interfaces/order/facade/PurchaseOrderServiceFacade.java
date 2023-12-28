package dev.tomle.ims.interfaces.order.facade;

import java.util.List;

import org.springframework.data.domain.Page;

import dev.tomle.ims.domain.model.order.exception.PurchaseOrderSaveException;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderLineDTO;

public interface PurchaseOrderServiceFacade {

	public List<PurchaseOrderDTO> getPurchaseOrders();
	public Page<PurchaseOrderDTO> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	public PurchaseOrderDTO getPurchaseOrderById(long id);
	public PurchaseOrderDTO savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);
	public PurchaseOrderLineDTO getPurchaseOrderLineById(long id);
	public PurchaseOrderLineDTO savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO);
	public PurchaseOrderLineDTO receivePurchaseOrderLine(long purchaseOrderLineId, BatchDTO batchDTO) throws PurchaseOrderSaveException;
	public void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder);
	public void deletePurchaseOrderById(long id);
	public void deletePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine);
	public void deletePurchaseOrderLineById(long id);
}
