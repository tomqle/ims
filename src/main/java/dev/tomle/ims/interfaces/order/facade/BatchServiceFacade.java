package dev.tomle.ims.interfaces.order.facade;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;

public interface BatchServiceFacade {
	public Page<BatchDTO> getBatches(int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<BatchDTO> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	public Page<BatchDTO> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	public BatchDTO getBatchById(long id);
}
