package dev.tomle.ims.application.order.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.order.SalesOrderService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.order.SalesOrder;
import dev.tomle.ims.domain.model.order.SalesOrderLine;
import dev.tomle.ims.infrastructure.order.repository.BatchRepository;
import dev.tomle.ims.infrastructure.order.repository.SalesOrderLineRepository;
import dev.tomle.ims.infrastructure.order.repository.SalesOrderRepository;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
	
	protected Logger logger = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
	
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	@Autowired
	private SalesOrderLineRepository salesOrderLineRepository;
	@Autowired
	private BatchRepository batchRepository;

	@Override
	public List<SalesOrder> getSalesOrders() {
		return salesOrderRepository.findAll();
	}

	@Override
	public Page<SalesOrder> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<SalesOrder> salesOrders = salesOrderRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, SalesOrder.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", salesOrders);
		return salesOrders;
	}

	@Override
	public SalesOrder getSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderById(id)", id);

		SalesOrder salesOrder = null;
		Optional<SalesOrder> salesOrderOpt = salesOrderRepository.findById(id);
		if(salesOrderOpt.isPresent()) {
			salesOrder = salesOrderOpt.get();
		}

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderById(id)", salesOrder);
		return salesOrder;
	}

	@Override
	public SalesOrder saveSalesOrder(SalesOrder salesOrder) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrder(salesOrder)", salesOrder);

		SalesOrder salesOrderSaved = salesOrderRepository.save(salesOrder);

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrder(salesOrder)", salesOrderSaved);
		return salesOrderSaved;
	}

	@Override
	public SalesOrderLine getSalesOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderById(id)", id);

		SalesOrderLine salesOrderLine = null;
		Optional<SalesOrderLine> salesOrderLineOpt = salesOrderLineRepository.findById(id);
		if(salesOrderLineOpt.isPresent()) {
			salesOrderLine  = salesOrderLineOpt.get();
		}

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderById(id)", salesOrderLine);
		return salesOrderLine;
	}

	@Override
	public SalesOrderLine saveSalesOrderLine(SalesOrderLine salesOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLine)", salesOrderLine);

		SalesOrderLine salesOrderLineSaved = salesOrderLineRepository.save(salesOrderLine);

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLine)", salesOrderLineSaved);
		return salesOrderLineSaved;
	}

	@Override
	public SalesOrderLine allocateSalesOrderLine(long salesOrderLineId, long batchId, long qty) {
		LogUtil.enterMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batchId, qty)", salesOrderLineId, batchId, qty);

		SalesOrderLine salesOrderLine = null;
		Optional<SalesOrderLine> salesOrderLineOpt = salesOrderLineRepository.findById(salesOrderLineId);
		if(salesOrderLineOpt.isPresent()) {
			salesOrderLine = salesOrderLineOpt.get();
			Batch batch = null;
			Optional<Batch> batchOpt = batchRepository.findById(batchId);
			if(batchOpt.isPresent()) {
				batch = batchOpt.get();
				salesOrderLine.allocate(batch);
				salesOrderLineRepository.save(salesOrderLine);
			}
		}

		LogUtil.exitMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batchId, qty)", salesOrderLine);
		return salesOrderLine;
	}

	@Override
	public void deleteSalesOrder(SalesOrder salesOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrder(salesOrder)", salesOrder);
		salesOrderRepository.delete(salesOrder);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrder(salesOrder)");
	}

	@Override
	public void deleteSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderById(id)", id);
		salesOrderRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderById(id)");
	}

	@Override
	public void deleteSalesOrderLine(SalesOrderLine salesOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderLine(id)", salesOrderLine);
		salesOrderLineRepository.delete(salesOrderLine);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderLine(id)");
	}

	@Override
	public void deleteSalesOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderLineById(id)", id);
		salesOrderLineRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderLineById(id)");
	}
	
	private String getClassName() {
		return SalesOrderServiceImpl.class.getName();
	}
}
