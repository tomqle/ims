package dev.tomle.ims.application.order.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.order.BatchService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.infrastructure.order.repository.BatchRepository;

@Service
public class BatchServiceImpl implements BatchService {
	
	protected Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

	@Autowired
	private BatchRepository batchRepository;

	@Override
	public Page<Batch> getBatches(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc", pageNum, pageSize, sortBy, desc);

		Page<Batch> batches = batchRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc", batches);
		return batches;
	}

	@Override
	public Page<Batch> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc,
												long productId) {
		return batchRepository.findByProductId(productId, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));
	}

	@Override
	public Page<Batch> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc,
															long productId) {
		return batchRepository.findUnallocatedByProductId(productId, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));
	}

	@Override
	public Batch getBatchById(long id) {
		Optional<Batch> batchOpt = batchRepository.findById(id);
		if(batchOpt.isEmpty()) {
			return null;
		}
		return batchOpt.get();
	}
	
	private String getClassName() {
		return BatchServiceImpl.class.getName();
	}
}
