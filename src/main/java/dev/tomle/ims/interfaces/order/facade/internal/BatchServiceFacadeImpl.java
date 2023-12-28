package dev.tomle.ims.interfaces.order.facade.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.order.BatchService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.interfaces.order.facade.BatchServiceFacade;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.BatchMapper;

@Service
public class BatchServiceFacadeImpl implements BatchServiceFacade {
	
	private Logger logger = LoggerFactory.getLogger(BatchServiceFacadeImpl.class);

	@Autowired
	private BatchService batchService;
	@Autowired
	private BatchMapper batchMapper;

	@Override
	public Page<BatchDTO> getBatches(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Batch> batches = batchService.getBatches(pageNum, pageSize, sortBy, desc);
		Page<BatchDTO> batchDTOs = batchMapper.toDto(batches);

		LogUtil.exitMethod(logger, getClassName(), "getBatches(pageNum, pageSize, sortBy, desc)", batchDTOs);
		return batchDTOs;
	}

	@Override
	public Page<BatchDTO> getBatchesByProductId(int pageNum, int pageSize, String sortBy, boolean desc,
			long productId) {
		LogUtil.enterMethod(logger, getClassName(), "getBatchesByProductId(pageNum, pageSize, sortBy, desc, productId)", pageNum, pageSize, sortBy, desc, productId);

		Page<Batch> batches = batchService.getBatchesByProductId(pageNum, pageSize, sortBy, desc, productId);
		Page<BatchDTO> batchDTOs = batchMapper.toDto(batches);

		LogUtil.exitMethod(logger, getClassName(), "getBatchesByProductId(pageNum, pageSize, sortBy, desc, productId)", batchDTOs);
		return batchDTOs;
	}

	@Override
	public Page<BatchDTO> getUnallocatedBatchesByProductId(int pageNum, int pageSize, String sortBy,
			boolean desc, long productId) {
		LogUtil.enterMethod(logger, getClassName(), "getUnallocatedBatchesByProductId(pageNum, pageSize, sortBy, desc, productId)", pageNum, pageSize, sortBy, desc, productId);

		Page<Batch> batches = batchService.getUnallocatedBatchesByProductId(pageNum, pageSize, sortBy, desc, productId);
		Page<BatchDTO> batchDTOs = batchMapper.toDto(batches);

		LogUtil.exitMethod(logger, getClassName(), "getUnallocatedBatchesByProductId(pageNum, pageSize, sortBy, desc, productId)", batchDTOs);
		return batchDTOs;
	}

	@Override
	public BatchDTO getBatchById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getBatchById(id)", id);

		BatchDTO batchDTO = batchMapper.toDto(batchService.getBatchById(id));

		LogUtil.exitMethod(logger, getClassName(), "getBatchById(id)", batchDTO);
		return batchDTO;
	}
	
	private String getClassName() {
		return BatchServiceFacadeImpl.class.getName();
	}
}
