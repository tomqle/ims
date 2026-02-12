package dev.tomle.ims.service;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.security.Privilege;

public interface BatchService {
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<BatchDTO> getBatches(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<BatchDTO> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<BatchDTO> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public BatchDTO getBatchById(long id);
}
