package dev.tomle.ims.interfaces.order.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.order.SalesOrderService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.interfaces.order.facade.SalesOrderServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.SalesOrderLineMapper;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.SalesOrderMapper;

@Service
public class SalesOrderServiceFacadeImpl implements SalesOrderServiceFacade {
	
	protected Logger logger = LoggerFactory.getLogger(SalesOrderServiceFacadeImpl.class);

	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private SalesOrderMapper salesOrderMapper;
	@Autowired
	private SalesOrderLineMapper salesOrderLineMapper;

	@Override
	public List<SalesOrderDTO> getSalesOrders() {
		return salesOrderService.getSalesOrders().stream().map(x -> salesOrderMapper.toDto(x)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Page<SalesOrderDTO> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<SalesOrder> salesOrders = salesOrderService.getSalesOrders(pageNum, pageSize, sortBy, desc);
		Page<SalesOrderDTO> salesOrderDTOs = salesOrderMapper.toDto(salesOrders);

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", salesOrderDTOs);
		return salesOrderDTOs;
	}

	@Override
	public SalesOrderDTO getSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderById(id)", id);

		SalesOrderDTO salesOrder = salesOrderMapper.toDto(salesOrderService.getSalesOrderById(id));

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderById(id)", salesOrder);
		return salesOrder;
	}

	@Override
	public SalesOrderDTO saveSalesOrder(SalesOrderDTO salesOrderDTO) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrder(salesOrderDTO)", salesOrderDTO);

		SalesOrderDTO salesOrderSaved = salesOrderMapper.toDto(salesOrderService.saveSalesOrder(salesOrderMapper.toDomain(salesOrderDTO)));

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrder(salesOrderDTO)", salesOrderDTO);
		return salesOrderSaved;
	}

	@Override
	public SalesOrderLineDTO getSalesOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderLineById(id)", id);

		SalesOrderLineDTO salesOrderLineDTO = salesOrderLineMapper.toDto(salesOrderService.getSalesOrderLineById(id));

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderLineById(id)", salesOrderLineDTO);
		return salesOrderLineDTO;
	}

	@Override
	public SalesOrderLineDTO saveSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLineDTO)", salesOrderLineDTO);

		SalesOrderLineDTO salesOrderLineSaved = salesOrderLineMapper.toDto(salesOrderService.saveSalesOrderLine(salesOrderLineMapper.toDomain(salesOrderLineDTO)));

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLineDTO)", salesOrderLineSaved);
		return salesOrderLineSaved;
	}

	@Override
	public SalesOrderLineDTO allocateSalesOrderLine(long salesOrderLineId, BatchDTO batchDTO) {
		LogUtil.enterMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batchDTO)", salesOrderLineId, batchDTO);

		SalesOrderLineDTO salesOrderLineDTO = salesOrderLineMapper.toDto(salesOrderService.allocateSalesOrderLine(salesOrderLineId, batchDTO.getId(), batchDTO.getQty()));

		LogUtil.exitMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batchDTO)", salesOrderLineDTO);
		return salesOrderLineDTO;
	}

	@Override
	public void deleteSalesOrder(SalesOrderDTO salesOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrder(salesOrderDTO)", salesOrder);
		salesOrderService.deleteSalesOrder(salesOrderMapper.toDomain(salesOrder));
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrder(salesOrderDTO)");
	}

	@Override
	public void deleteSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderById(id)", id);
		salesOrderService.deleteSalesOrderById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderById(id)");
	}

	@Override
	public void deleteSalesOrderLine(SalesOrderLineDTO salesOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderLine(salesOrderLineDTO)", salesOrderLine);
		salesOrderService.deleteSalesOrderLine(salesOrderLineMapper.toDomain(salesOrderLine));
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderLine(salesOrderLineDTO)");
	}

	@Override
	public void deleteSalesOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderLineById(id)", id);
		salesOrderService.deleteSalesOrderLineById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderLineById(id)");
	}
	
	private String getClassName() {
		return SalesOrderServiceFacadeImpl.class.getName();
	}
}
