package dev.tomle.ims.model.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.BatchOrderLine;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.dto.BatchOrderLineDTO;

@Component
public final class BatchOrderLineMapper implements BaseEntityMapper<BatchOrderLine, BatchOrderLineDTO> {
	
	@Override
	public BatchOrderLineDTO toDto(BatchOrderLine batchOrderLine) {
		BatchOrderLineDTO batchOrderLineDTO = null;
		if(batchOrderLine != null) {
			batchOrderLineDTO = new BatchOrderLineDTO();
			setBaseEntityMembers(batchOrderLine, batchOrderLineDTO);
			batchOrderLineDTO.qty = batchOrderLine.getQty();
			batchOrderLineDTO.batchId = batchOrderLine.getBatch() != null ? batchOrderLine.getBatch().getId() : 0;
			batchOrderLineDTO.salesOrderLineId = batchOrderLine.getSalesOrderLine() != null ? batchOrderLine.getSalesOrderLine().getId() : 0;
		}
		return batchOrderLineDTO;
	}
	
	@Override
	public BatchOrderLine toDomain(BatchOrderLineDTO batchOrderLineDTO) {
		BatchOrderLine batchOrderLine = null;
		if(batchOrderLineDTO != null) {
			Batch batch = new Batch();
			batch.setId(batchOrderLineDTO.batchId);
			SalesOrderLine salesOrderLine = new SalesOrderLine();
			salesOrderLine.setId(batchOrderLineDTO.salesOrderLineId);

			batchOrderLine = new BatchOrderLine();
			setBaseEntityMembers(batchOrderLineDTO, batchOrderLine);
			batchOrderLine.setQty(batchOrderLineDTO.qty);
			batchOrderLine.setBatch(batch);
			batchOrderLine.setSalesOrderLine(salesOrderLine);
		}
		
		return batchOrderLine;
	}
}
