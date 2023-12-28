package dev.tomle.ims.application.product;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.domain.model.product.ProductStatus;
import dev.tomle.ims.domain.model.security.Privilege;

public interface ProductService {
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<Product> getProducts(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<Product> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<Product> getProductsByName(String name, int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Product getProductById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_WRITE + "')")
	public Product saveProduct(Product product);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_DELETE + "')")
	public void deleteProduct(Product product);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_DELETE + "')")
	public void deleteProductById(long id);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public Page<ProductStatus> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc);
	@PreAuthorize("hasAuthority('" + Privilege.PRODUCT_READ + "')")
	public ProductStatus getProductStatus(long id);
}
