package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.order.BatchOrderLine;
import dev.tomle.ims.domain.model.order.SalesOrderLine;
import dev.tomle.ims.interfaces.order.facade.dto.BatchOrderLineDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class BatchOrderLineMapper implements BaseEntityMapper<BatchOrderLine, BatchOrderLineDTO> {
	
	@Override
	public BatchOrderLineDTO toDto(BatchOrderLine batchOrderLine) {
		BatchOrderLineDTO batchOrderLineDTO = null;
		if(batchOrderLine != null) {
			batchOrderLineDTO = new BatchOrderLineDTO();
			setBaseEntityMembers(batchOrderLine, batchOrderLineDTO);
			batchOrderLineDTO.setQty(batchOrderLine.getQty());
			batchOrderLineDTO.setBatchId(batchOrderLine.getBatch() != null ? batchOrderLine.getBatch().getId() : 0);
			batchOrderLineDTO.setSalesOrderLineId(batchOrderLine.getSalesOrderLine() != null ? batchOrderLine.getSalesOrderLine().getId() : 0);
		}
		return batchOrderLineDTO;
	}
	
	@Override
	public BatchOrderLine toDomain(BatchOrderLineDTO batchOrderLineDTO) {
		BatchOrderLine batchOrderLine = null;
		if(batchOrderLineDTO != null) {
			Batch batch = new Batch();
			batch.setId(batchOrderLineDTO.getBatchId());
			SalesOrderLine salesOrderLine = new SalesOrderLine();
			salesOrderLine.setId(batchOrderLineDTO.getSalesOrderLineId());

			batchOrderLine = new BatchOrderLine();
			setBaseEntityMembers(batchOrderLineDTO, batchOrderLine);
			batchOrderLine.setQty(batchOrderLineDTO.getQty());
			batchOrderLine.setBatch(batch);
			batchOrderLine.setSalesOrderLine(salesOrderLine);
		}
		
		return batchOrderLine;
	}
}
