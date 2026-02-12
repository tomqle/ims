package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Customer;
import dev.tomle.ims.model.SalesOrder;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.dto.SalesOrderDTO;
import dev.tomle.ims.model.dto.SalesOrderLineDTO;

@Component
public final class SalesOrderMapper implements BaseEntityMapper<SalesOrder, SalesOrderDTO> {
	
	@Autowired
	private SalesOrderLineMapper salesOrderLineMapper;
	
	public SalesOrderDTO toDto(SalesOrder salesOrder) {
		SalesOrderDTO salesOrderDTO = null;
		if(salesOrder != null) {
			salesOrderDTO = new SalesOrderDTO();
			setBaseEntityMembers(salesOrder, salesOrderDTO);
			salesOrderDTO.statusId = salesOrder.getStatusId();
			salesOrderDTO.address1 = salesOrder.getAddress1();
			salesOrderDTO.shippingDescription = salesOrder.getShippingDescription();
			salesOrderDTO.shippingCost = salesOrder.getShippingCost();
			salesOrderDTO.total = salesOrder.getTotal();
			salesOrderDTO.customerId = salesOrder.getCustomer() != null ? salesOrder.getCustomer().getId() : 0;
			salesOrderDTO.salesOrderLines = salesOrderLineMapper.toDto(salesOrder.getSalesOrderLines());
		}
		return salesOrderDTO;
	}
	
	public SalesOrder toDomain(SalesOrderDTO salesOrderDTO) {
		SalesOrder salesOrder = null;
		if(salesOrderDTO != null) {
			Customer customer = new Customer();
			customer.setId(salesOrderDTO.customerId);

			salesOrder = new SalesOrder();
			setBaseEntityMembers(salesOrderDTO, salesOrder);
			salesOrder.setStatusId(salesOrderDTO.statusId);
			salesOrder.setAddress1(salesOrderDTO.address1);
			salesOrder.setShippingDescription(salesOrderDTO.shippingDescription);
			salesOrder.setShippingCost(salesOrderDTO.shippingCost);
			salesOrder.setTotal(salesOrderDTO.total);
			salesOrder.setCustomer(customer);
			for(SalesOrderLineDTO soLineDTO: salesOrderDTO.salesOrderLines) {
				SalesOrderLine salesOrderLine = salesOrderLineMapper.toDomain(soLineDTO);
				salesOrderLine.setSalesOrder(salesOrder);
				salesOrder.addSalesOrderLine(salesOrderLine);
			}
		}
		return salesOrder;
	}

}
