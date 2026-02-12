package dev.tomle.ims.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.PurchaseOrder;
import dev.tomle.ims.model.PurchaseOrderLine;
import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.PurchaseOrderDTO;
import dev.tomle.ims.model.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.model.dto.mapper.BatchMapper;
import dev.tomle.ims.model.dto.mapper.PurchaseOrderLineMapper;
import dev.tomle.ims.model.dto.mapper.PurchaseOrderMapper;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.model.repository.PurchaseOrderLineRepository;
import dev.tomle.ims.model.repository.PurchaseOrderRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private PurchaseOrderLineMapper purchaseOrderLineMapper;
	@Autowired
	private BatchMapper batchMapper;
	
	@Override
	public List<PurchaseOrderDTO> getPurchaseOrders() {
		return purchaseOrderMapper.toDto(purchaseOrderRepository.findAll());
	}
	
	@Override
	public Page<PurchaseOrderDTO> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, PurchaseOrder.SORT_BY));
		Page<PurchaseOrderDTO> purchaseOrderDTOs = purchaseOrderMapper.toDto(purchaseOrders);

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", purchaseOrders);
		return purchaseOrderDTOs;
	}

	@Override
	public PurchaseOrderDTO getPurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderById(id)",id);

		Optional<PurchaseOrder> poOpt = purchaseOrderRepository.findById(id);
		PurchaseOrder purchaseOrder = poOpt.isPresent() ? poOpt.get() : null;
		PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);
		
		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderById(id)", purchaseOrder);
		return purchaseOrderDTO;
	}
	
	@Override
	public PurchaseOrderDTO savePurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrder)", purchaseOrder);

		PurchaseOrder purchaseOrderSaved = purchaseOrderRepository.save(purchaseOrderMapper.toDomain(purchaseOrder));
		PurchaseOrderDTO purchaseOrderSavedDTO = purchaseOrderMapper.toDto(purchaseOrderSaved);

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrder)", purchaseOrderSaved);
		return purchaseOrderSavedDTO;
	}
	
	@Override
	public PurchaseOrderLineDTO getPurchaseOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", id);

		Optional<PurchaseOrderLine> poLineOpt = purchaseOrderLineRepository.findById(id);
		PurchaseOrderLine purchaseOrderLine = poLineOpt.isPresent() ? poLineOpt.get() : null;
		PurchaseOrderLineDTO purchaseOrderLineDTO = purchaseOrderLineMapper.toDto(purchaseOrderLine);

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", purchaseOrderLine);
		return purchaseOrderLineDTO;
	}
	
	@Override
	public PurchaseOrderLineDTO savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLine);

		PurchaseOrderLine purchaseOrderLineSaved = purchaseOrderLineRepository.save(purchaseOrderLineMapper.toDomain(purchaseOrderLine));
		PurchaseOrderLineDTO purchaseOrderLineSavedDTO = purchaseOrderLineMapper.toDto(purchaseOrderLineSaved);

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLineSaved);
		return purchaseOrderLineSavedDTO;
	}
	
	@Override
	public PurchaseOrderLineDTO receivePurchaseOrderLine(long purchaseOrderLineId, BatchDTO batch) throws PurchaseOrderSaveException {
		LogUtil.enterMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batch)", purchaseOrderLineId, batch);

		Optional<PurchaseOrderLine> poLineOpt = purchaseOrderLineRepository.findById(purchaseOrderLineId);
		PurchaseOrderLine purchaseOrderLine = null;
		PurchaseOrderLineDTO purchaseOrderLineDTO = null;
		if(poLineOpt.isPresent()) {
			purchaseOrderLine = poLineOpt.get();
			purchaseOrderLine.receive(batchMapper.toDomain(batch));
			purchaseOrderLine = purchaseOrderLineRepository.save(purchaseOrderLine);
			purchaseOrderLineDTO = purchaseOrderLineMapper.toDto(purchaseOrderLine);
		}

		LogUtil.exitMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batch)", purchaseOrderLine);
		return purchaseOrderLineDTO;
	}

	@Override
	public void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)", purchaseOrder);
		purchaseOrderRepository.delete(purchaseOrderMapper.toDomain(purchaseOrder));
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)");
	}

	@Override
	public void deletePurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderById(id)", id);
		purchaseOrderRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrderById(id)");
	}

	@Override
	public void deletePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLine);
		purchaseOrderLineRepository.delete(purchaseOrderLineMapper.toDomain(purchaseOrderLine));
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrderLine(purchaseOrderLine)");
	}

	@Override
	public void deletePurchaseOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLineById(id)", id);
		purchaseOrderLineRepository.deleteById(id);
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLineById(id)");
	}
	
	private String getClassName() {
		return PurchaseOrderServiceImpl.class.getName();
	}
}
