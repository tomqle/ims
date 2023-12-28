package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.order.PurchaseOrder;
import dev.tomle.ims.domain.model.order.PurchaseOrderLine;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

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
			purchaseOrderLineDTO.setLineNumber(purchaseOrderLine.getLineNumber());
			purchaseOrderLineDTO.setSku(purchaseOrderLine.getSku());
			purchaseOrderLineDTO.setProductName(purchaseOrderLine.getProductName());
			purchaseOrderLineDTO.setQty(purchaseOrderLine.getQty());
			purchaseOrderLineDTO.setQtyReceived(purchaseOrderLine.getQtyReceived());
			purchaseOrderLineDTO.setCost(purchaseOrderLine.getCost());
			purchaseOrderLineDTO.setProductId(purchaseOrderLine.getProduct() != null ? purchaseOrderLine.getProduct().getId() : 0);
			purchaseOrderLineDTO.setPurchaseOrderId(purchaseOrderLine.getPurchaseOrder() != null ? purchaseOrderLine.getPurchaseOrder().getId() : 0);
			purchaseOrderLineDTO.setBatches(batchMapper.toDto(purchaseOrderLine.getBatches()));
		}
		return purchaseOrderLineDTO;
	}
	
	@Override
	public PurchaseOrderLine toDomain(PurchaseOrderLineDTO purchaseOrderLineDTO) {
		PurchaseOrderLine purchaseOrderLine = null;
		if(purchaseOrderLineDTO != null) {
			Product product = new Product();
			product.setId(purchaseOrderLineDTO.getProductId());
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setId(purchaseOrderLineDTO.getPurchaseOrderId());

			purchaseOrderLine = new PurchaseOrderLine();
			setBaseEntityMembers(purchaseOrderLineDTO, purchaseOrderLine);
			purchaseOrderLine.setLineNumber(purchaseOrderLineDTO.getLineNumber());
			purchaseOrderLine.setSku(purchaseOrderLineDTO.getSku());
			purchaseOrderLine.setProductName(purchaseOrderLineDTO.getProductName());
			purchaseOrderLine.setQty(purchaseOrderLineDTO.getQty());
			purchaseOrderLine.setCost(purchaseOrderLineDTO.getCost());
			purchaseOrderLine.setQtyReceived(purchaseOrderLineDTO.getQtyReceived());
			purchaseOrderLine.setProduct(null);
			purchaseOrderLine.setPurchaseOrder(null);
			purchaseOrderLine.setBatches(batchMapper.toDomain(purchaseOrderLineDTO.getBatches()));
		}
		return purchaseOrderLine;
	}
}
