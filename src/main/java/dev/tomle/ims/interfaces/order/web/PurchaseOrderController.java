package dev.tomle.ims.interfaces.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.domain.model.order.exception.PurchaseOrderSaveException;
import dev.tomle.ims.interfaces.order.facade.PurchaseOrderServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
@RequestMapping("/purchaseOrder")
public final class PurchaseOrderController {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private PurchaseOrderServiceFacade purchaseOrderServiceFacade;
	
	@GetMapping
	public Page<PurchaseOrderDTO> getPurchaseOrders(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return purchaseOrderServiceFacade.getPurchaseOrders(validPageNumber, validPageSize, validSortBy, validDesc);
	}
	
	@GetMapping(path = "/{id}")
	public PurchaseOrderDTO getPurchaseOrder(Authentication authentication, @PathVariable long id) {
		logger.debug(authentication.getName());
		return purchaseOrderServiceFacade.getPurchaseOrderById(id);
	}
	
	@PostMapping
	public PurchaseOrderDTO postPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		return purchaseOrderServiceFacade.savePurchaseOrder(purchaseOrderDTO);
	}
	
	@PutMapping(path = "/{id}")
	public PurchaseOrderDTO putPurchaseOrder(@PathVariable long id, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		purchaseOrderDTO.setId(id);
		return purchaseOrderServiceFacade.savePurchaseOrder(purchaseOrderDTO);
	}
	
	@PutMapping(path = "/{id}/receive/{purchaseOrderLineId}")
	public PurchaseOrderLineDTO receivePurchaseOrderLine(Authentication authentication, @PathVariable long id, @PathVariable long purchaseOrderLineId, @RequestBody BatchDTO batchDTO) throws PurchaseOrderSaveException {
		if(batchDTO.getPurchaseOrderLineId() == 0) {
			batchDTO.setPurchaseOrderLineId(purchaseOrderLineId);
		}
		return purchaseOrderServiceFacade.receivePurchaseOrderLine(purchaseOrderLineId, batchDTO);
	}
}
