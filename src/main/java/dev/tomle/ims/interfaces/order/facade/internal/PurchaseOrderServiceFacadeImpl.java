package dev.tomle.ims.interfaces.order.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.order.PurchaseOrderService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.contact.Supplier;
import dev.tomle.ims.domain.model.order.PurchaseOrder;
import dev.tomle.ims.domain.model.order.exception.PurchaseOrderSaveException;
import dev.tomle.ims.infrastructure.contact.repository.SupplierRepository;
import dev.tomle.ims.interfaces.order.facade.PurchaseOrderServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.BatchMapper;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.PurchaseOrderLineMapper;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.PurchaseOrderMapper;

@Service
public class PurchaseOrderServiceFacadeImpl implements PurchaseOrderServiceFacade {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceFacadeImpl.class);

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private PurchaseOrderLineMapper purchaseOrderLineMapper;
	@Autowired
	private BatchMapper batchMapper;
	
	@Override
	public List<PurchaseOrderDTO> getPurchaseOrders() {
		return purchaseOrderService.getPurchaseOrders().stream().map(x -> purchaseOrderMapper.toDto(x)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public Page<PurchaseOrderDTO> getPurchaseOrders(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<PurchaseOrder> purchaseOrders = purchaseOrderService.getPurchaseOrders(pageNum, pageSize, sortBy, desc);
		Page<PurchaseOrderDTO> purchaseOrderDTOs = purchaseOrderMapper.toDto(purchaseOrders);

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrders(pageNum, pageSize, sortBy, desc)", purchaseOrderDTOs);
		return purchaseOrderDTOs;
	}
	
	@Override
	public PurchaseOrderDTO getPurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderById(id)", id);

		PurchaseOrderDTO purchaseOrder = purchaseOrderMapper.toDto(purchaseOrderService.getPurchaseOrderById(id));

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderById(id)", purchaseOrder);
		return purchaseOrder;
	}
	
	@Override
	public PurchaseOrderDTO savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrderDTO)", purchaseOrderDTO);

		PurchaseOrder purchaseOrder = purchaseOrderMapper.toDomain(purchaseOrderDTO);
		if(purchaseOrder.getSupplier() != null) {
			Optional<Supplier> supplierOpt = supplierRepository.findById(purchaseOrder.getSupplier().getId());
			purchaseOrder.setSupplier(supplierOpt.isPresent() ? supplierOpt.get() : null);
		}
		PurchaseOrderDTO purchaseOrderSaved = purchaseOrderMapper.toDto(purchaseOrderService.savePurchaseOrder(purchaseOrder));

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrder(purchaseOrderDTO)", purchaseOrderSaved);
		return purchaseOrderSaved;
	}
	
	@Override
	public PurchaseOrderLineDTO getPurchaseOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", id);

		PurchaseOrderLineDTO purchaseOrderLine = purchaseOrderLineMapper.toDto(purchaseOrderService.getPurchaseOrderLineById(id));

		LogUtil.exitMethod(logger, getClassName(), "getPurchaseOrderLineById(id)", purchaseOrderLine);
		return purchaseOrderLine;
	}
	
	@Override
	public PurchaseOrderLineDTO savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO) {
		LogUtil.enterMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLineDTO)", purchaseOrderLineDTO);

		PurchaseOrderLineDTO purchaseOrderLineSaved = purchaseOrderLineMapper.toDto(purchaseOrderService.savePurchaseOrderLine(purchaseOrderLineMapper.toDomain(purchaseOrderLineDTO)));

		LogUtil.exitMethod(logger, getClassName(), "savePurchaseOrderLine(purchaseOrderLineDTO)", purchaseOrderLineSaved);
		return purchaseOrderLineSaved;
	}

	@Override
	public PurchaseOrderLineDTO receivePurchaseOrderLine(long purchaseOrderLineId, BatchDTO batchDTO) throws PurchaseOrderSaveException {
		LogUtil.enterMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batchDTO)", purchaseOrderLineId, batchDTO);

		PurchaseOrderLineDTO purchaseOrderLine = purchaseOrderLineMapper.toDto(purchaseOrderService.receivePurchaseOrderLine(purchaseOrderLineId, batchMapper.toDomain(batchDTO)));

		LogUtil.exitMethod(logger, getClassName(), "receivePurchaseOrderLine(purchaseOrderLineId, batchDTO)", purchaseOrderLine);
		return purchaseOrderLine;
	}

	@Override
	public void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)", purchaseOrder);
		purchaseOrderService.deletePurchaseOrder(purchaseOrderMapper.toDomain(purchaseOrder));
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrder(purchaseOrder)");
	}

	@Override
	public void deletePurchaseOrderById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderById(id)", id);
		purchaseOrderService.deletePurchaseOrderById(id);
		LogUtil.exitMethod(logger, getClassName(), PurchaseOrderServiceFacadeImpl.class.getName(), "deletePurchaseOrderById(id)");
	}

	@Override
	public void deletePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLine) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLine(purchaseOrderLineDTO)", purchaseOrderLine);
		purchaseOrderService.deletePurchaseOrderLine(purchaseOrderLineMapper.toDomain(purchaseOrderLine));
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrderLine(purchaseOrderLineDTO)");
	}

	@Override
	public void deletePurchaseOrderLineById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deletePurchaseOrderLineById(id)", id);
		purchaseOrderService.deletePurchaseOrderById(id);
		LogUtil.exitMethod(logger, getClassName(), "deletePurchaseOrderLineById(id)");
	}
	
	private String getClassName() {
		return PurchaseOrderServiceFacadeImpl.class.getName();
	}

}
