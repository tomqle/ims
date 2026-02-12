package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.PurchaseOrder;
import dev.tomle.ims.model.PurchaseOrderLine;
import dev.tomle.ims.model.dto.PurchaseOrderLineDTO;

@Component
public final class PurchaseOrderLineMapper implements BaseEntityMapper<PurchaseOrderLine, PurchaseOrderLineDTO> {
	
	@Autowired
	private BatchMapper batchMapper;

	@Override
	public PurchaseOrderLineDTO toDto(PurchaseOrderLine purchaseOrderLine) {
		PurchaseOrderLineDTO purchaseOrderLineDTO = null;
		if(purchaseOrderLine != null) {
			purchaseOrderLineDTO = new PurchaseOrderLineDTO();
			setBaseEntityMembers(purchaseOrderLine, purchaseOrderLineDTO);
			purchaseOrderLineDTO.lineNumber = purchaseOrderLine.getLineNumber();
			purchaseOrderLineDTO.sku = purchaseOrderLine.getSku();
			purchaseOrderLineDTO.productName = purchaseOrderLine.getProductName();
			purchaseOrderLineDTO.qty = purchaseOrderLine.getQty();
			purchaseOrderLineDTO.qtyReceived = purchaseOrderLine.getQtyReceived();
			purchaseOrderLineDTO.cost = purchaseOrderLine.getCost();
			purchaseOrderLineDTO.productId = purchaseOrderLine.getProduct() != null ? purchaseOrderLine.getProduct().getId() : 0;
			purchaseOrderLineDTO.purchaseOrderId = purchaseOrderLine.getPurchaseOrder() != null ? purchaseOrderLine.getPurchaseOrder().getId() : 0;
			purchaseOrderLineDTO.batches = batchMapper.toDto(purchaseOrderLine.getBatches());
		}
		return purchaseOrderLineDTO;
	}
	
	@Override
	public PurchaseOrderLine toDomain(PurchaseOrderLineDTO purchaseOrderLineDTO) {
		PurchaseOrderLine purchaseOrderLine = null;
		if(purchaseOrderLineDTO != null) {
			Product product = new Product();
			product.setId(purchaseOrderLineDTO.productId);
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setId(purchaseOrderLineDTO.purchaseOrderId);

			purchaseOrderLine = new PurchaseOrderLine();
			setBaseEntityMembers(purchaseOrderLineDTO, purchaseOrderLine);
			purchaseOrderLine.setLineNumber(purchaseOrderLineDTO.lineNumber);
			purchaseOrderLine.setSku(purchaseOrderLineDTO.sku);
			purchaseOrderLine.setProductName(purchaseOrderLineDTO.productName);
			purchaseOrderLine.setQty(purchaseOrderLineDTO.qty);
			purchaseOrderLine.setCost(purchaseOrderLineDTO.cost);
			purchaseOrderLine.setQtyReceived(purchaseOrderLineDTO.qtyReceived);
			purchaseOrderLine.setProduct(null);
			purchaseOrderLine.setPurchaseOrder(null);
			purchaseOrderLine.setBatches(batchMapper.toDomain(purchaseOrderLineDTO.batches));
		}
		return purchaseOrderLine;
	}
}
