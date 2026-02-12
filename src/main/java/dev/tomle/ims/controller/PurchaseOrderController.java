package dev.tomle.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.PurchaseOrderDTO;
import dev.tomle.ims.model.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.service.PurchaseOrderService;
import dev.tomle.ims.util.RestControllerUtil;

@RestController
@RequestMapping("/purchaseOrder")
public final class PurchaseOrderController {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@GetMapping
	public PagedModel<PurchaseOrderDTO> getPurchaseOrders(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return new PagedModel<>(purchaseOrderService.getPurchaseOrders(validPageNumber, validPageSize, validSortBy, validDesc));
	}
	
	@GetMapping(path = "/{id}")
	public PurchaseOrderDTO getPurchaseOrder(Authentication authentication, @PathVariable long id) {
		logger.debug(authentication.getName());
		return purchaseOrderService.getPurchaseOrderById(id);
	}
	
	@PostMapping
	public PurchaseOrderDTO postPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		return purchaseOrderService.savePurchaseOrder(purchaseOrderDTO);
	}
	
	@PutMapping(path = "/{id}")
	public PurchaseOrderDTO putPurchaseOrder(@PathVariable long id, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		purchaseOrderDTO.id = id;
		return purchaseOrderService.savePurchaseOrder(purchaseOrderDTO);
	}
	
	@PutMapping(path = "/{id}/receive/{purchaseOrderLineId}")
	public PurchaseOrderLineDTO receivePurchaseOrderLine(Authentication authentication, @PathVariable long id, @PathVariable long purchaseOrderLineId, @RequestBody BatchDTO batchDTO) throws PurchaseOrderSaveException {
		if(batchDTO.purchaseOrderLineId == 0) {
			batchDTO.purchaseOrderLineId = purchaseOrderLineId;
		}
		return purchaseOrderService.receivePurchaseOrderLine(purchaseOrderLineId, batchDTO);
	}
}
