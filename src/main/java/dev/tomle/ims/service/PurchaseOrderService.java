package dev.tomle.ims.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.PurchaseOrderDTO;
import dev.tomle.ims.model.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.model.security.Privilege;

public interface PurchaseOrderService {
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public List<PurchaseOrderDTO> getPurchaseOrders();
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public Page<PurchaseOrderDTO> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public PurchaseOrderDTO getPurchaseOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public PurchaseOrderDTO savePurchaseOrder(PurchaseOrderDTO purchaseOrder);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public PurchaseOrderLineDTO getPurchaseOrderLineById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public PurchaseOrderLineDTO savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_RECEIVE + "')")
	public PurchaseOrderLineDTO receivePurchaseOrderLine(long purchaseOrderLineId, BatchDTO batch) throws PurchaseOrderSaveException;
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_DELETE + "')")
	public void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_DELETE + "')")
	public void deletePurchaseOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public void deletePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public void deletePurchaseOrderLineById(long id);
}
