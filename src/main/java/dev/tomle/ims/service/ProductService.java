package dev.tomle.ims.service;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.model.dto.ProductDTO;
import dev.tomle.ims.model.dto.ProductStatusDTO;
import dev.tomle.ims.model.security.Privilege;

public interface ProductService {
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<ProductDTO> getProducts(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<ProductDTO> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<ProductDTO> getProductsByName(String name, int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public ProductDTO getProductById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_WRITE + "')")
	public ProductDTO saveProduct(ProductDTO product);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_DELETE + "')")
	public void deleteProduct(ProductDTO product);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_DELETE + "')")
	public void deleteProductById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<ProductStatusDTO> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public ProductStatusDTO getProductStatus(long id);
}
