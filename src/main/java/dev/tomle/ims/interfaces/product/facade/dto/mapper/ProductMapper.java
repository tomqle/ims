package dev.tomle.ims.interfaces.product.facade.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.BatchMapper;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.PurchaseOrderLineMapper;
import dev.tomle.ims.interfaces.order.facade.dto.mapper.SalesOrderLineMapper;
import dev.tomle.ims.interfaces.product.facade.dto.ProductDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public final class ProductMapper implements BaseEntityMapper<Product, ProductDTO> {
	
	@Autowired
	private PurchaseOrderLineMapper purchaseOrderLineMapper;
	@Autowired
	private SalesOrderLineMapper salesOrderLineMapper;
	@Autowired
	private BatchMapper batchMapper;

	public ProductDTO toDto(Product product) {
		ProductDTO productDTO = null;
		if(product != null) {
			productDTO = new ProductDTO();
			setBaseEntityMembers(product, productDTO);
			productDTO.setStatusId(product.getProductStatusId());
			productDTO.setSku(product.getSku());
			productDTO.setName(product.getName());
			productDTO.setPurchaseOrderLines(purchaseOrderLineMapper.toDto(product.getPurchaseOrderLines()));
			productDTO.setSalesOrderLines(salesOrderLineMapper.toDto(product.getSalesOrderLines()));
			productDTO.setBatches(batchMapper.toDto(product.getBatches()));
		}
		return productDTO;
	}
	
	public Product toDomain(ProductDTO productDTO) {
		Product product = null;
		if(productDTO != null) {
			product = new Product();
			setBaseEntityMembers(productDTO, product);
			product.setProductStatusId(productDTO.getStatusId());
			product.setSku(productDTO.getSku());
			product.setName(productDTO.getName());
			product.setPurchaseOrderLines(purchaseOrderLineMapper.toDomain(productDTO.getPurchaseOrderLines()));
			product.setSalesOrderLines(salesOrderLineMapper.toDomain(productDTO.getSalesOrderLines()));
			product.setBatches(batchMapper.toDomain(productDTO.getBatches()));
		}
		return product;
		
	}
}
