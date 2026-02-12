package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.SalesOrder;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.dto.SalesOrderLineDTO;

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
			salesOrderLineDTO.lineNumber = salesOrderLine.getLineNumber();
			salesOrderLineDTO.sku = salesOrderLine.getSku();
			salesOrderLineDTO.productName = salesOrderLine.getProductName();
			salesOrderLineDTO.qty = salesOrderLine.getQty();
			salesOrderLineDTO.qtyAllocated = salesOrderLine.getQtyAllocated();
			salesOrderLineDTO.cost = salesOrderLine.getCost();
			salesOrderLineDTO.price = salesOrderLine.getPrice();
			salesOrderLineDTO.lineTotal = salesOrderLine.getLineTotal();
			salesOrderLineDTO.productId = salesOrderLine.getProduct() != null ? salesOrderLine.getProduct().getId() : 0;
			salesOrderLineDTO.salesOrderId = salesOrderLine.getSalesOrder() != null ? salesOrderLine.getSalesOrder().getId() : 0;
			salesOrderLineDTO.batchOrderLines = batchOrderLineMapper.toDto(salesOrderLine.getBatchOrderLines());
		}
		return salesOrderLineDTO;
	}
	
	@Override
	public SalesOrderLine toDomain(SalesOrderLineDTO salesOrderLineDTO) {
		SalesOrderLine salesOrderLine = null;
		if(salesOrderLineDTO != null) {
			Product product = new Product();
			product.setId(salesOrderLineDTO.productId);
			SalesOrder salesOrder = new SalesOrder();
			salesOrder.setId(salesOrderLineDTO.id);

			salesOrderLine = new SalesOrderLine();
			setBaseEntityMembers(salesOrderLineDTO, salesOrderLine);
			salesOrderLine.setLineNumber(salesOrderLineDTO.lineNumber);
			salesOrderLine.setSku(salesOrderLineDTO.sku);
			salesOrderLine.setProductName(salesOrderLineDTO.productName);
			salesOrderLine.setQty(salesOrderLineDTO.qty);
			salesOrderLine.setQtyAllocated(salesOrderLineDTO.qtyAllocated);
			salesOrderLine.setCost(salesOrderLineDTO.cost);
			salesOrderLine.setPrice(salesOrderLineDTO.price);
			salesOrderLine.setLineTotal(salesOrderLineDTO.lineTotal);
			salesOrderLine.setProduct(product);
			salesOrderLine.setSalesOrder(salesOrder);
			salesOrderLine.setBatchOrderLines(batchOrderLineMapper.toDomain(salesOrderLineDTO.batchOrderLines));
		}
		return salesOrderLine;
	}
}
