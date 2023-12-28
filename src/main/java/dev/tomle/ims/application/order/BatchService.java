package dev.tomle.ims.application.order;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.security.Privilege;

public interface BatchService {
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<Batch> getBatches(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<Batch> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Page<Batch> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId);
	@PreAuthorize("hasAuthority('" + Privilege.BATCH_READ + "')")
	public Batch getBatchById(long id);
}
