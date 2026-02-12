package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.PurchaseOrderLine;
import dev.tomle.ims.model.dto.BatchDTO;

@Component
public final class BatchMapper implements BaseEntityMapper<Batch, BatchDTO> {
	
	@Autowired
	private BatchOrderLineMapper batchOrderLineMapper;

	@Override
	public BatchDTO toDto(Batch batch) {
		BatchDTO batchDTO = null;
		if(batch != null) {
			batchDTO = new BatchDTO();
			setBaseEntityMembers(batch, batchDTO);
			batchDTO.qty = batch.getQty();
			batchDTO.qtyAllocated = batch.getQtyAllocated();
			batchDTO.location = batch.getLocation();
			batchDTO.productId = batch.getProduct() != null ? batch.getProduct().getId() : 0;
			batchDTO.purchaseOrderLineId = batch.getPurchaseOrderLine() != null ? batch.getPurchaseOrderLine().getId() : 0;
			batchDTO.batchOrderLines = batchOrderLineMapper.toDto(batch.getBatchOrderLines());
		}
		return batchDTO;
	}

	@Override
	public Batch toDomain(BatchDTO batchDTO) {
		Batch batch = null;
		if(batchDTO != null) {
			Product product = new Product();
			product.setId(batchDTO.productId);
			PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
			purchaseOrderLine.setId(batchDTO.purchaseOrderLineId);

			batch = new Batch();
			setBaseEntityMembers(batchDTO, batch);
			batch.setQty(batchDTO.qty);
			batch.setQtyAllocated(batchDTO.qtyAllocated);
			batch.setLocation(batchDTO.location);
			batch.setProduct(product);
			batch.setPurchaseOrderLine(purchaseOrderLine);
			batch.setBatchOrderLines(batchOrderLineMapper.toDomain(batchDTO.batchOrderLines));
		}
		return batch;
	}
}
