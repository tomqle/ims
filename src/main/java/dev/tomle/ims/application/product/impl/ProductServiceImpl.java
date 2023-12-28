package dev.tomle.ims.application.product.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.application.product.ProductService;
import dev.tomle.ims.application.shared.LogUtil;
import dev.tomle.ims.application.shared.ServiceUtil;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.domain.model.product.ProductStatus;
import dev.tomle.ims.infrastructure.product.repository.ProductRepository;
import dev.tomle.ims.infrastructure.product.repository.ProductStatusRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductStatusRepository productStatusRepository;

	@Override
	public Page<Product> getProducts(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)",products);
		return products;
	}

	@Override
	public Page<Product> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", sku, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findActiveBySku(sku, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		
		LogUtil.exitMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", products);
		return products;
	}

	@Override
	public Page<Product> getProductsByName(String name, int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", name, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findActiveByName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		
		LogUtil.exitMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", products);
		return products;
	}

	@Override
	public Product getProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsById(id)", id);

		Optional<Product> productOpt = productRepository.findById(id);
		Product product = productOpt.isPresent() ? productOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getProductsById(id)", product);
		return product;
	}

	@Override
	public Product saveProduct(Product product) {
		LogUtil.enterMethod(logger, getClassName(), "saveProduct(product)", product);

		Product productSaved = productRepository.save(product);
		
		LogUtil.exitMethod(logger, getClassName(), "saveProduct(product)", productSaved);
		return productSaved;
	}

	@Override
	public void deleteProduct(Product product) {
		LogUtil.enterMethod(logger, getClassName(), "deleteProduct(product)", product);
		productRepository.delete(product);
		LogUtil.exitMethod(logger, getClassName(), "deleteProduct(product)");
	}

	@Override
	public void deleteProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteProductById(id)", id);
		productRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteProductById(id)");
	}
	
	@Override
	public Page<ProductStatus> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatuses(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);
		
		Page<ProductStatus> statuses = productStatusRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));

		LogUtil.exitMethod(logger, getClassName(), "getProductStatuses()", statuses);
		return statuses;
	}
	
	@Override
	public ProductStatus getProductStatus(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatus(id)", id);
		
		Optional<ProductStatus> productStatustOpt = productStatusRepository.findById(id);
		ProductStatus status = productStatustOpt.isPresent() ? productStatustOpt.get() : null;

		LogUtil.exitMethod(logger, getClassName(), "getProductStatus(id)", status);
		return status;
	}
	
	private String getClassName() {
		return ProductServiceImpl.class.getName();
	}
}
