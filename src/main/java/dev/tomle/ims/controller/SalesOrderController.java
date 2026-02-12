package dev.tomle.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.SalesOrderDTO;
import dev.tomle.ims.model.dto.SalesOrderLineDTO;
import dev.tomle.ims.service.SalesOrderService;
import dev.tomle.ims.util.RestControllerUtil;

@RestController
@RequestMapping("/salesOrder")
public final class SalesOrderController {
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping
	public PagedModel<SalesOrderDTO> getSalesOrders(@RequestParam(required = false) Integer pageNumber,
												@RequestParam(required = false) Integer pageSize,
												@RequestParam(required = false) String sortBy,
												@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return new PagedModel<>(salesOrderService.getSalesOrders(validPageNumber, validPageSize, validSortBy, validDesc));
	}

	@GetMapping(path = "/{id}")
	public SalesOrderDTO getSalesOrder(@PathVariable long id) {
		return salesOrderService.getSalesOrderById(id);
	}
	
	@PostMapping
	public SalesOrderDTO postSalesOrder(@RequestBody SalesOrderDTO salesOrderDTO) {
		return salesOrderService.saveSalesOrder(salesOrderDTO);
	}

	@PutMapping(path = "/{id}")
	public SalesOrderDTO putSalesOrder(@PathVariable long id, @RequestBody SalesOrderDTO salesOrderDTO) {
		salesOrderDTO.id = id;
		return salesOrderService.saveSalesOrder(salesOrderDTO);
	}
	
	@PutMapping(path = "/{id}/allocate/{salesOrderLineId}")
	public SalesOrderLineDTO allocateSalesOrderLine(@PathVariable long id, @PathVariable long salesOrderLineId, @RequestBody BatchDTO batchDTO) {
		return salesOrderService.allocateSalesOrderLine(salesOrderLineId, batchDTO);
	}
}
