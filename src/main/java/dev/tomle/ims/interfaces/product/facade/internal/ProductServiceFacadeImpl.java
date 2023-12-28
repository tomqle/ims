package dev.tomle.ims.interfaces.product.facade.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.product.ProductService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.domain.model.product.ProductStatus;
import dev.tomle.ims.interfaces.product.facade.ProductServiceFacade;
import dev.tomle.ims.interfaces.product.facade.dto.ProductDTO;
import dev.tomle.ims.interfaces.product.facade.dto.ProductStatusDTO;
import dev.tomle.ims.interfaces.product.facade.dto.mapper.ProductMapper;
import dev.tomle.ims.interfaces.product.facade.dto.mapper.ProductStatusMapper;

@Service
public class ProductServiceFacadeImpl implements ProductServiceFacade {
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceFacadeImpl.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductStatusMapper productStatusMapper;

	@Override
	public Page<ProductDTO> getProducts(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Product> products = productService.getProducts(pageNum, pageSize, sortBy, desc);
		Page<ProductDTO> productDTOs = productMapper.toDto(products);

		LogUtil.exitMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)", products);
		return productDTOs;
	}

	@Override
	public Page<ProductDTO> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy,
			boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", sku, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productService.getProductsBySku(sku, pageNum, pageSize, sortBy, desc);
		Page<ProductDTO> productDTOs = productMapper.toDto(products);

		LogUtil.exitMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", products);
		return productDTOs;
	}

	@Override
	public Page<ProductDTO> getProductsByName(String name, int pageNum, int pageSize, String sortBy,
			boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", name, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productService.getProductsByName(name, pageNum, pageSize, sortBy, desc);
		Page<ProductDTO> productDTOs = productMapper.toDto(products);

		LogUtil.exitMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", productDTOs);
		return productDTOs;
	}

	@Override
	public ProductDTO getProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductById(id)", id);

		ProductDTO productDTO = productMapper.toDto(productService.getProductById(id));

		LogUtil.exitMethod(logger, getClassName(), "getProductById(id)", productDTO);
		return productDTO;
	}

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		LogUtil.enterMethod(logger, getClassName(), "saveProduct(productDTO)", productDTO);

		ProductDTO savedProductDTO = productMapper.toDto(productService.saveProduct(productMapper.toDomain(productDTO)));

		LogUtil.enterMethod(logger, getClassName(), "getProductById(id)", savedProductDTO);
		return savedProductDTO;
	}

	@Override
	public void deleteProduct(ProductDTO productDTO){
		LogUtil.enterMethod(logger, getClassName(), "deleteProduct(productDTO)", productDTO);
		productService.deleteProduct(productMapper.toDomain(productDTO));
		LogUtil.exitMethod(logger, getClassName(), "deleteProduct(productDTO)");
	}

	@Override
	public void deleteProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteProductById(id)", id);
		productService.deleteProductById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteProductById(id)");
	}

	@Override
	public Page<ProductStatusDTO> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatuses(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);
		
		Page<ProductStatus> productStatuses = productService.getProductStatuses(pageNum, pageSize, sortBy, desc);
		Page<ProductStatusDTO> productStatusDTOs = productStatusMapper.toDto(productStatuses);

		LogUtil.exitMethod(logger, getClassName(), "getProductStatuses(pageNum, pageSize, sortBy, desc)", productStatusDTOs);
		return productStatusDTOs;
	}

	@Override
	public ProductStatusDTO getProductStatus(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatus(id)", id);
		
		ProductStatus productStatus = productService.getProductStatus(id);
		ProductStatusDTO productStatusDTO = productStatusMapper.toDto(productStatus);

		LogUtil.exitMethod(logger, getClassName(), "getProductStatus(id)", productStatusDTO);
		return productStatusDTO;
	}
	
	private String getClassName() {
		return ProductServiceFacadeImpl.class.getName();
	}
}
