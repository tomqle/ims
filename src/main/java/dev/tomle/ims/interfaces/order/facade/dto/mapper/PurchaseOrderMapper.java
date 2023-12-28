package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.contact.Supplier;
import dev.tomle.ims.domain.model.order.PurchaseOrder;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class PurchaseOrderMapper implements BaseEntityMapper<PurchaseOrder, PurchaseOrderDTO> {

	@Autowired
	private PurchaseOrderLineMapper purchaseOrderLineMapper;

	public PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO purchaseOrderDTO = null;
		if(purchaseOrder != null) {
			purchaseOrderDTO = new PurchaseOrderDTO();
			setBaseEntityMembers(purchaseOrder, purchaseOrderDTO);
			purchaseOrderDTO.setAddress1(purchaseOrder.getAddress1());
			purchaseOrderDTO.setShippingDescription(purchaseOrder.getShippingDescription());
			purchaseOrderDTO.setShippingCost(purchaseOrder.getShippingCost());
			purchaseOrderDTO.setTotal(purchaseOrder.getTotal());
			purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplier() != null ? purchaseOrder.getSupplier().getId() : 0);
			purchaseOrderDTO.setPurchaseOrderLines(purchaseOrderLineMapper.toDto(purchaseOrder.getPurchaseOrderLines()));
		}
        return purchaseOrderDTO;
    }

    public PurchaseOrder toDomain(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = null;
    	if(purchaseOrderDTO != null) {
    		Supplier supplier = new Supplier();
    		supplier.setId(purchaseOrderDTO.getSupplierId());
    		
    		purchaseOrder = new PurchaseOrder();
    		setBaseEntityMembers(purchaseOrderDTO, purchaseOrder);
    		purchaseOrder.setAddress1(purchaseOrderDTO.getAddress1());
    		purchaseOrder.setShippingDescription(purchaseOrderDTO.getShippingDescription());
    		purchaseOrder.setShippingCost(purchaseOrderDTO.getShippingCost());
    		purchaseOrder.setSupplier(supplier);
    		purchaseOrder.setPurchaseOrderLines(purchaseOrderLineMapper.toDomain(purchaseOrderDTO.getPurchaseOrderLines()));
    		purchaseOrder.calculateTotal();
    	}
    	return purchaseOrder;
    }
}
