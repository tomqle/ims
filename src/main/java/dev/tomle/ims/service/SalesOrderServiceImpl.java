package dev.tomle.ims.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.SalesOrder;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.SalesOrderDTO;
import dev.tomle.ims.model.dto.SalesOrderLineDTO;
import dev.tomle.ims.model.dto.mapper.SalesOrderLineMapper;
import dev.tomle.ims.model.dto.mapper.SalesOrderMapper;
import dev.tomle.ims.model.repository.BatchRepository;
import dev.tomle.ims.model.repository.SalesOrderLineRepository;
import dev.tomle.ims.model.repository.SalesOrderRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
	
	protected Logger logger = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
	
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	@Autowired
	private SalesOrderLineRepository salesOrderLineRepository;
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private SalesOrderMapper salesOrderMapper;
	@Autowired
	private SalesOrderLineMapper salesOrderLineMapper;

	@Override
	public List<SalesOrderDTO> getSalesOrders() {
		return salesOrderMapper.toDto(salesOrderRepository.findAll());
	}

	@Override
	public Page<SalesOrderDTO> getSalesOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<SalesOrder> salesOrders = salesOrderRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, SalesOrder.SORT_BY));
		Page<SalesOrderDTO> salesOrderDTOs = salesOrderMapper.toDto(salesOrders);

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrders(pageNum, pageSize, sortBy, desc)", salesOrders);
		return salesOrderDTOs;
	}

	@Override
	public SalesOrderDTO getSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderById(id)", id);

		SalesOrder salesOrder = null;
		SalesOrderDTO salesOrderDTO = null;
		Optional<SalesOrder> salesOrderOpt = salesOrderRepository.findById(id);
		if(salesOrderOpt.isPresent()) {
			salesOrder = salesOrderOpt.get();
			salesOrderDTO = salesOrderMapper.toDto(salesOrder);
		}

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderById(id)", salesOrder);
		return salesOrderDTO;
	}

	@Override
	public SalesOrderDTO saveSalesOrder(SalesOrderDTO salesOrder) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrder(salesOrder)", salesOrder);

		SalesOrder salesOrderSaved = salesOrderRepository.save(salesOrderMapper.toDomain(salesOrder));
		SalesOrderDTO salesOrderSavedDTO = salesOrderMapper.toDto(salesOrderSaved);

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrder(salesOrder)", salesOrderSaved);
		return salesOrderSavedDTO;
	}

	@Override
	public SalesOrderLineDTO getSalesOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getSalesOrderById(id)", id);

		SalesOrderLine salesOrderLine = null;
		SalesOrderLineDTO salesOrderLineDTO = null;
		Optional<SalesOrderLine> salesOrderLineOpt = salesOrderLineRepository.findById(id);
		if(salesOrderLineOpt.isPresent()) {
			salesOrderLine = salesOrderLineOpt.get();
			salesOrderLineDTO = salesOrderLineMapper.toDto(salesOrderLine);
		}

		LogUtil.exitMethod(logger, getClassName(), "getSalesOrderById(id)", salesOrderLine);
		return salesOrderLineDTO;
	}

	@Override
	public SalesOrderLineDTO saveSalesOrderLine(SalesOrderLineDTO salesOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLine)", salesOrderLine);

		SalesOrderLine salesOrderLineSaved = salesOrderLineRepository.save(salesOrderLineMapper.toDomain(salesOrderLine));
		SalesOrderLineDTO salesOrderLineSavedDTO = salesOrderLineMapper.toDto(salesOrderLineSaved);

		LogUtil.exitMethod(logger, getClassName(), "saveSalesOrderLine(salesOrderLine)", salesOrderLineSaved);
		return salesOrderLineSavedDTO;
	}

	@Override
	public SalesOrderLineDTO allocateSalesOrderLine(long salesOrderLineId, BatchDTO batchDTO) {
		LogUtil.enterMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batch)", salesOrderLineId, batchDTO);

		SalesOrderLine salesOrderLine = null;
		SalesOrderLineDTO salesOrderLineDTO = null;
		Optional<SalesOrderLine> salesOrderLineOpt = salesOrderLineRepository.findById(salesOrderLineId);
		if(salesOrderLineOpt.isPresent()) {
			salesOrderLine = salesOrderLineOpt.get();
			salesOrderLineDTO = salesOrderLineMapper.toDto(salesOrderLine);
			Batch batch = null;
			Optional<Batch> batchOpt = batchRepository.findById(batchDTO.id);
			if(batchOpt.isPresent()) {
				batch = batchOpt.get();
				salesOrderLine.allocate(batch);
				salesOrderLineRepository.save(salesOrderLine);
			}
		}

		LogUtil.exitMethod(logger, getClassName(), "allocateSalesOrderLine(salesOrderLineId, batch)", salesOrderLine);
		return salesOrderLineDTO;
	}

	@Override
	public void deleteSalesOrder(SalesOrderDTO salesOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrder(salesOrder)", salesOrder);
		salesOrderRepository.delete(salesOrderMapper.toDomain(salesOrder));
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrder(salesOrder)");
	}

	@Override
	public void deleteSalesOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderById(id)", id);
		salesOrderRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteSalesOrderById(id)");
	}

	@Override
	public void deleteSalesOrderLine(SalesOrderLineDTO salesOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deleteSalesOrderLine(id)", salesOrderLine);
		salesOrderLineRepository.delete(salesOrderLineMapper.toDomain(salesOrderLine));
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
