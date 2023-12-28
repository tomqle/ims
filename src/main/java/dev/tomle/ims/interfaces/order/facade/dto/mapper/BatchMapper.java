package dev.tomle.ims.interfaces.order.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.order.Batch;
import dev.tomle.ims.domain.model.order.PurchaseOrderLine;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.interfaces.order.facade.dto.BatchDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

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
			batchDTO.setQty(batch.getQty());
			batchDTO.setQtyAllocated(batch.getQtyAllocated());
			batchDTO.setLocation(batch.getLocation());
			batchDTO.setProductId(batch.getProduct() != null ? batch.getProduct().getId() : 0);
			batchDTO.setPurchaseOrderLineId(batch.getPurchaseOrderLine() != null ? batch.getPurchaseOrderLine().getId() : 0);
			batchDTO.setBatchOrderLines(batchOrderLineMapper.toDto(batch.getBatchOrderLines()));
		}
		return batchDTO;
	}

	@Override
	public Batch toDomain(BatchDTO batchDTO) {
		Batch batch = null;
		if(batchDTO != null) {
			Product product = new Product();
			product.setId(batchDTO.getProductId());
			PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
			purchaseOrderLine.setId(batchDTO.getPurchaseOrderLineId());

			batch = new Batch();
			setBaseEntityMembers(batchDTO, batch);
			batch.setQty(batchDTO.getQty());
			batch.setQtyAllocated(batchDTO.getQtyAllocated());
			batch.setLocation(batchDTO.getLocation());
			batch.setProduct(product);
			batch.setPurchaseOrderLine(purchaseOrderLine);
			batch.setBatchOrderLines(batchOrderLineMapper.toDomain(batchDTO.getBatchOrderLines()));
		}
		return batch;
	}
}
