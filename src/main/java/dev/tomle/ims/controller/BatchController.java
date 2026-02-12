package dev.tomle.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.service.BatchService;
import dev.tomle.ims.util.RestControllerUtil;

@RestController
@RequestMapping("/batch")
public final class BatchController {
	
	@Autowired
	private BatchService batchService;

	@GetMapping
	public PagedModel<BatchDTO> getBatches(@RequestParam(required = false) Integer pageNumber,
												@RequestParam(required = false) Integer pageSize,
												@RequestParam(required = false) String sortBy,
												@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return new PagedModel<>(batchService.getBatches(validPageNumber, validPageSize, validSortBy, validDesc));
	}

	@GetMapping(path = "/product/{productId}")
	public PagedModel<BatchDTO> getBatchesByProductId(@PathVariable long productId,
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
			return new PagedModel<>(batchService.getUnallocatedBatchesByProductId(validPageNumber, validPageSize, validSortBy, validDesc, productId));
		}
		return new PagedModel<>(batchService.getBatchesByProductId(validPageNumber, validPageSize, validSortBy, validDesc, productId));
	}

	@GetMapping(path = "/{id}")
	public BatchDTO getBatchById(@PathVariable long id) {
		return batchService.getBatchById(id);
	}
}
