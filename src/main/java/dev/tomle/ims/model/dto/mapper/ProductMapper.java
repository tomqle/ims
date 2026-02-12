package dev.tomle.ims.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.dto.ProductDTO;

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
			productDTO.statusId = product.getId();
			productDTO.sku = product.getSku();
			productDTO.name = product.getName();
			productDTO.purchaseOrderLines = purchaseOrderLineMapper.toDto(product.getPurchaseOrderLines());
			productDTO.salesOrderLines = salesOrderLineMapper.toDto(product.getSalesOrderLines());
			productDTO.batches = batchMapper.toDto(product.getBatches());
		}
		return productDTO;
	}
	
	public Product toDomain(ProductDTO productDTO) {
		Product product = null;
		if(productDTO != null) {
			product = new Product();
			setBaseEntityMembers(productDTO, product);
			product.setProductStatusId(productDTO.statusId);
			product.setSku(productDTO.sku);
			product.setName(productDTO.name);
			product.setPurchaseOrderLines(purchaseOrderLineMapper.toDomain(productDTO.purchaseOrderLines));
			product.setSalesOrderLines(salesOrderLineMapper.toDomain(productDTO.salesOrderLines));
			product.setBatches(batchMapper.toDomain(productDTO.batches));
		}
		return product;
		
	}
}
