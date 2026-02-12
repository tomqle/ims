package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.PurchaseOrder;
import dev.tomle.ims.model.Supplier;
import dev.tomle.ims.model.dto.PurchaseOrderDTO;

@Component
public final class PurchaseOrderMapper implements BaseEntityMapper<PurchaseOrder, PurchaseOrderDTO> {

	@Autowired
	private PurchaseOrderLineMapper purchaseOrderLineMapper;

	public PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO purchaseOrderDTO = null;
		if(purchaseOrder != null) {
			purchaseOrderDTO = new PurchaseOrderDTO();
			setBaseEntityMembers(purchaseOrder, purchaseOrderDTO);
			purchaseOrderDTO.address1 = purchaseOrder.getAddress1();
			purchaseOrderDTO.shippingDescription = purchaseOrder.getShippingDescription();
			purchaseOrderDTO.shippingCost = purchaseOrder.getShippingCost();
			purchaseOrderDTO.total = purchaseOrder.getTotal();
			purchaseOrderDTO.supplierId = purchaseOrder.getSupplier() != null ? purchaseOrder.getSupplier().getId() : 0;
			purchaseOrderDTO.purchaseOrderLines = purchaseOrderLineMapper.toDto(purchaseOrder.getPurchaseOrderLines());
		}
        return purchaseOrderDTO;
    }

    public PurchaseOrder toDomain(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = null;
    	if(purchaseOrderDTO != null) {
    		Supplier supplier = new Supplier();
    		supplier.setId(purchaseOrderDTO.supplierId);
    		
    		purchaseOrder = new PurchaseOrder();
    		setBaseEntityMembers(purchaseOrderDTO, purchaseOrder);
    		purchaseOrder.setAddress1(purchaseOrderDTO.address1);
    		purchaseOrder.setShippingDescription(purchaseOrderDTO.shippingDescription);
    		purchaseOrder.setShippingCost(purchaseOrderDTO.shippingCost);
    		purchaseOrder.setSupplier(supplier);
    		purchaseOrder.setPurchaseOrderLines(purchaseOrderLineMapper.toDomain(purchaseOrderDTO.purchaseOrderLines));
    		purchaseOrder.calculateTotal();
    	}
    	return purchaseOrder;
    }
}
