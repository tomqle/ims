package dev.tomle.ims.application.order.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.domain.model.order.PurchaseOrderLine;
import dev.tomle.ims.domain.model.order.exception.PurchaseOrderSaveException;
import dev.tomle.ims.infrastructure.order.repository.PurchaseOrderLineRepository;
import dev.tomle.ims.infrastructure.order.repository.PurchaseOrderRepository;
import dev.tomle.ims.application.order.PurchaseOrderService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.order.PurchaseOrder;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;
	
	@Override
	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrderRepository.findAll();
	}
	
	@Override
	public Page<PurchaseOrder> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, PurchaseOrder.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", purchaseOrders);
		return purchaseOrders;
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderById(id)",id);

		Optional<PurchaseOrder> poOpt = purchaseOrderRepository.findById(id);
		PurchaseOrder purchaseOrder = poOpt.isPresent() ? poOpt.get() : null;
		
		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderById(id)", purchaseOrder);
		return purchaseOrder;
	}
	
	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrder)", purchaseOrder);

		PurchaseOrder purchaseOrderSaved = purchaseOrderRepository.save(purchaseOrder);

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrder)", purchaseOrderSaved);
		return purchaseOrderSaved;
	}
	
	@Override
	public PurchaseOrderLine getPurchaseOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", id);

		Optional<PurchaseOrderLine> poLineOpt = purchaseOrderLineRepository.findById(id);
		PurchaseOrderLine purchaseOrderLine = poLineOpt.isPresent() ? poLineOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", purchaseOrderLine);
		return purchaseOrderLine;
	}
	
	@Override
	public PurchaseOrderLine savePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLine);

		PurchaseOrderLine purchaseOrderLineSaved = purchaseOrderLineRepository.save(purchaseOrderLine);

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLineSaved);
		return purchaseOrderLineSaved;
	}
	
	@Override
	public PurchaseOrderLine receivePurchaseOrderLine(long purchaseOrderLineId, Batch batch) throws PurchaseOrderSaveException {
		LogUtil.enterMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batch)", purchaseOrderLineId, batch);

		Optional<PurchaseOrderLine> poLineOpt = purchaseOrderLineRepository.findById(purchaseOrderLineId);
		PurchaseOrderLine purchaseOrderLine = null;
		if(poLineOpt.isPresent()) {
			purchaseOrderLine = poLineOpt.get();
			purchaseOrderLine.receive(batch);
			purchaseOrderLine = purchaseOrderLineRepository.save(purchaseOrderLine);
		}

		LogUtil.exitMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batch)", purchaseOrderLine);
		return purchaseOrderLine;
	}

	@Override
	public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)", purchaseOrder);
		purchaseOrderRepository.delete(purchaseOrder);
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)");
	}

	@Override
	public void deletePurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderById(id)", id);
		purchaseOrderRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrderById(id)");
	}

	@Override
	public void deletePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLine(purchaseOrderLine)", purchaseOrderLine);
		purchaseOrderLineRepository.delete(purchaseOrderLine);
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
