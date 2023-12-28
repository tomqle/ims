package dev.tomle.ims.application.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.order.PurchaseOrder;
import dev.tomle.ims.domain.model.order.PurchaseOrderLine;
import dev.tomle.ims.domain.model.order.exception.PurchaseOrderSaveException;
import dev.tomle.ims.domain.model.security.Privilege;

public interface PurchaseOrderService {
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public List<PurchaseOrder> getPurchaseOrders();
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public Page<PurchaseOrder> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public PurchaseOrder getPurchaseOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_READ + "')")
	public PurchaseOrderLine getPurchaseOrderLineById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public PurchaseOrderLine savePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_RECEIVE + "')")
	public PurchaseOrderLine receivePurchaseOrderLine(long purchaseOrderLineId, Batch batch) throws PurchaseOrderSaveException;
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_DELETE + "')")
	public void deletePurchaseOrder(PurchaseOrder purchaseOrder);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_DELETE + "')")
	public void deletePurchaseOrderById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public void deletePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine);
	@PreAuthorize("hasAuthority('" + Privilege.PURCHASE_ORDER_WRITE + "')")
	public void deletePurchaseOrderLineById(long id);
}
