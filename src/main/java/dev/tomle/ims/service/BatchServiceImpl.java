package dev.tomle.ims.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.dto.BatchDTO;
import dev.tomle.ims.model.dto.mapper.BatchMapper;
import dev.tomle.ims.model.repository.BatchRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class BatchServiceImpl implements BatchService {
	
	protected Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private BatchMapper batchMapper;

	@Override
	public Page<BatchDTO> getBatches(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc", pageNum, pageSize, sortBy, desc);

		Page<Batch> inventories = batchRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));
		Page<BatchDTO> inventoryDTOs = batchMapper.toDto(inventories);

		LogUtil.exitMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc", inventories);
		return inventoryDTOs;
	}

	@Override
	public Page<BatchDTO> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc,
												long productId) {
		Page<Batch> batches = batchRepository.findByProductId(productId, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));
		Page<BatchDTO> batchDTOs = batchMapper.toDto(batches);
		
		return batchDTOs;
	}

	@Override
	public Page<BatchDTO> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc, long productId) {
		Page<Batch> batches = batchRepository.findUnallocatedByProductId(productId, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Batch.SORT_BY));
		Page<BatchDTO> batchDTOs = batchMapper.toDto(batches);
		
		return batchDTOs;
	}

	@Override
	public BatchDTO getBatchById(long id) {
		Optional<Batch> batchOpt = batchRepository.findById(id);
		if(batchOpt.isEmpty()) {
			return null;
		}
		return batchMapper.toDto(batchOpt.get());
	}
	
	private String getClassName() {
		return BatchServiceImpl.class.getName();
	}
}
