package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.contact.Customer;
import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.domain.model.order.SalesOrderLine;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class SalesOrderMapper implements BaseEntityMapper<SalesOrder, SalesOrderDTO> {
	
	@Autowired
	private SalesOrderLineMapper salesOrderLineMapper;
	
	public SalesOrderDTO toDto(SalesOrder salesOrder) {
		SalesOrderDTO salesOrderDTO = null;
		if(salesOrder != null) {
			salesOrderDTO = new SalesOrderDTO();
			setBaseEntityMembers(salesOrder, salesOrderDTO);
			salesOrderDTO.setStatusId(salesOrder.getStatusId());
			salesOrderDTO.setAddress1(salesOrder.getAddress1());
			salesOrderDTO.setShippingDescription(salesOrder.getShippingDescription());
			salesOrderDTO.setShippingCost(salesOrder.getShippingCost());
			salesOrderDTO.setTotal(salesOrder.getTotal());
			salesOrderDTO.setCustomerId(salesOrder.getCustomer() != null ? salesOrder.getCustomer().getId() : 0);
			salesOrderDTO.setSalesOrderLines(salesOrderLineMapper.toDto(salesOrder.getSalesOrderLines()));
		}
		return salesOrderDTO;
	}
	
	public SalesOrder toDomain(SalesOrderDTO salesOrderDTO) {
		SalesOrder salesOrder = null;
		if(salesOrderDTO != null) {
			Customer customer = new Customer();
			customer.setId(salesOrderDTO.getCustomerId());

			salesOrder = new SalesOrder();
			setBaseEntityMembers(salesOrderDTO, salesOrder);
			salesOrder.setStatusId(salesOrderDTO.getStatusId());
			salesOrder.setAddress1(salesOrderDTO.getAddress1());
			salesOrder.setShippingDescription(salesOrderDTO.getShippingDescription());
			salesOrder.setShippingCost(salesOrderDTO.getShippingCost());
			salesOrder.setTotal(salesOrderDTO.getTotal());
			salesOrder.setCustomer(customer);
			for(SalesOrderLineDTO soLineDTO: salesOrderDTO.getSalesOrderLines()) {
				SalesOrderLine salesOrderLine = salesOrderLineMapper.toDomain(soLineDTO);
				salesOrderLine.setSalesOrder(salesOrder);
				salesOrder.addSalesOrderLine(salesOrderLine);
			}
		}
		return salesOrder;
	}

}
