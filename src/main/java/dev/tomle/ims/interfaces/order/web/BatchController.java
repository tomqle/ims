package dev.tomle.ims.interfaces.order.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.interfaces.order.facade.BatchServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
@RequestMapping("/batch")
public final class BatchController {
	
	@Autowired
	private BatchServiceFacade batchServiceFacade;

	@GetMapping
	public Page<BatchDTO> getBatches(@RequestParam(required = false) Integer pageNumber,
												@RequestParam(required = false) Integer pageSize,
												@RequestParam(required = false) String sortBy,
												@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return batchServiceFacade.getBatches(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "product/{productId}")
	public Page<BatchDTO> getBatchesByProductId(@PathVariable long productId,
											@RequestParam(required = false) Integer pageNumber,
											@RequestParam(required = false) Integer pageSize,
											@RequestParam(required = false) String sortBy,
											@RequestParam(required = false) Boolean desc,
											@RequestParam(required = false) Boolean unallocated) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		if(unallocated != null && unallocated) {
			return batchServiceFacade.getUnallocatedBatchesByProductId(validPageNumber, validPageSize, validSortBy, validDesc, productId);
		}
		return batchServiceFacade.getBatchesByProductId(validPageNumber, validPageSize, validSortBy, validDesc, productId);
	}

	@GetMapping(path = "/{id}")
	public BatchDTO getBatchById(@PathVariable long id) {
		return batchServiceFacade.getBatchById(id);
	}
}
