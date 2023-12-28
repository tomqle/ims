package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.domain.model.order.SalesOrderLine;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class SalesOrderLineMapper implements BaseEntityMapper<SalesOrderLine, SalesOrderLineDTO> {
	
	@Autowired
	private BatchOrderLineMapper batchOrderLineMapper;
	
	@Override
	public SalesOrderLineDTO toDto(SalesOrderLine salesOrderLine) {
		SalesOrderLineDTO salesOrderLineDTO = null;
		if(salesOrderLine != null) {
			salesOrderLineDTO = new SalesOrderLineDTO();
			setBaseEntityMembers(salesOrderLine, salesOrderLineDTO);
			salesOrderLineDTO.setLineNumber(salesOrderLine.getLineNumber());
			salesOrderLineDTO.setSku(salesOrderLine.getSku());
			salesOrderLineDTO.setProductName(salesOrderLine.getProductName());
			salesOrderLineDTO.setQty(salesOrderLine.getQty());
			salesOrderLineDTO.setQtyAllocated(salesOrderLine.getQtyAllocated());
			salesOrderLineDTO.setCost(salesOrderLine.getCost());
			salesOrderLineDTO.setPrice(salesOrderLine.getPrice());
			salesOrderLineDTO.setLineTotal(salesOrderLine.getLineTotal());
			salesOrderLineDTO.setProductId(salesOrderLine.getProduct() != null ? salesOrderLine.getProduct().getId() : 0);
			salesOrderLineDTO.setSalesOrderId(salesOrderLine.getSalesOrder() != null ? salesOrderLine.getSalesOrder().getId() : 0);
			salesOrderLineDTO.setBatchOrderLines(batchOrderLineMapper.toDto(salesOrderLine.getBatchOrderLines()));
		}
		return salesOrderLineDTO;
	}
	
	@Override
	public SalesOrderLine toDomain(SalesOrderLineDTO salesOrderLineDTO) {
		SalesOrderLine salesOrderLine = null;
		if(salesOrderLineDTO != null) {
			Product product = new Product();
			product.setId(salesOrderLineDTO.getProductId());
			SalesOrder salesOrder = new SalesOrder();
			salesOrder.setId(salesOrderLineDTO.getId());

			salesOrderLine = new SalesOrderLine();
			setBaseEntityMembers(salesOrderLineDTO, salesOrderLine);
			salesOrderLine.setLineNumber(salesOrderLineDTO.getLineNumber());
			salesOrderLine.setSku(salesOrderLineDTO.getSku());
			salesOrderLine.setProductName(salesOrderLineDTO.getProductName());
			salesOrderLine.setQty(salesOrderLineDTO.getQty());
			salesOrderLine.setQtyAllocated(salesOrderLineDTO.getQtyAllocated());
			salesOrderLine.setCost(salesOrderLineDTO.getCost());
			salesOrderLine.setPrice(salesOrderLineDTO.getPrice());
			salesOrderLine.setLineTotal(salesOrderLineDTO.getLineTotal());
			salesOrderLine.setProduct(product);
			salesOrderLine.setSalesOrder(salesOrder);
			salesOrderLine.setBatchOrderLines(batchOrderLineMapper.toDomain(salesOrderLineDTO.getBatchOrderLines()));
		}
		return salesOrderLine;
	}
}
