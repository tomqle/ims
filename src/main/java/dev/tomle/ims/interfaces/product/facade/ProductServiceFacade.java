package dev.tomle.ims.interfaces.product.facade;

import org.springframework.data.domain.Page;

import dev.tomle.ims.interfaces.product.facade.dto.ProductDTO;
import dev.tomle.ims.interfaces.product.facade.dto.ProductStatusDTO;

public interface ProductServiceFacade {
	public Page<ProductDTO> getProducts(int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<ProductDTO> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy, boolean desc);
	public Page<ProductDTO> getProductsByName(String name, int pageNum, int pageSize, String sortBy, boolean desc);
	public ProductDTO getProductById(long id);
	public ProductDTO saveProduct(ProductDTO product);
	public void deleteProduct(ProductDTO product);
	public void deleteProductById(long id);
	public Page<ProductStatusDTO> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc);
	public ProductStatusDTO getProductStatus(long id);
}
