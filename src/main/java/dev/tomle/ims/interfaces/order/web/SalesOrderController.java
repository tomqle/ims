package dev.tomle.ims.interfaces.order.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.interfaces.order.facade.SalesOrderServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderDTO;
import dev.tomle.ims.interfaces.order.facade.dto.SalesOrderLineDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/salesOrder")
@SecurityRequirement(name="openapi")
public final class SalesOrderController {
	
	@Autowired
	private SalesOrderServiceFacade salesOrderServiceFacade;
	
	@GetMapping
	public Page<SalesOrderDTO> getSalesOrders(@RequestParam(required = false) Integer pageNumber,
												@RequestParam(required = false) Integer pageSize,
												@RequestParam(required = false) String sortBy,
												@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return salesOrderServiceFacade.getSalesOrders(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "/{id}")
	public SalesOrderDTO getSalesOrder(@PathVariable long id) {
		return salesOrderServiceFacade.getSalesOrderById(id);
	}
	
	@PostMapping
	public SalesOrderDTO postSalesOrder(@RequestBody SalesOrderDTO salesOrderDTO) {
		return salesOrderServiceFacade.saveSalesOrder(salesOrderDTO);
	}

	@PutMapping(path = "/{id}")
	public SalesOrderDTO putSalesOrder(@PathVariable long id, @RequestBody SalesOrderDTO salesOrderDTO) {
		salesOrderDTO.setId(id);
		return salesOrderServiceFacade.saveSalesOrder(salesOrderDTO);
	}
	
	@PutMapping(path = "/{id}/allocate/{salesOrderLineId}")
	public SalesOrderLineDTO allocateSalesOrderLine(@PathVariable long id, @PathVariable long salesOrderLineId, @RequestBody BatchDTO batchDTO) {
		return salesOrderServiceFacade.allocateSalesOrderLine(salesOrderLineId, batchDTO);
	}
}
