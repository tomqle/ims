package dev.tomle.ims.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.ProductStatus;
import dev.tomle.ims.model.dto.ProductDTO;
import dev.tomle.ims.model.dto.ProductStatusDTO;
import dev.tomle.ims.model.dto.mapper.ProductMapper;
import dev.tomle.ims.model.dto.mapper.ProductStatusMapper;
import dev.tomle.ims.model.repository.ProductRepository;
import dev.tomle.ims.model.repository.ProductStatusRepository;
import dev.tomle.ims.util.LogUtil;
import dev.tomle.ims.util.ServiceUtil;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductStatusRepository productStatusRepository;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductStatusMapper productStatusMapper;

	@Override
	public Page<ProductDTO> getProducts(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		Page<ProductDTO> productDTOs = productMapper.toDto(products);

		LogUtil.exitMethod(logger, getClassName(), "getProducts(pageNum, pageSize, sortBy, desc)",products);
		return productDTOs;
	}

	@Override
	public Page<ProductDTO> getProductsBySku(String sku, int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", sku, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findActiveBySku(sku, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		Page<ProductDTO> productDTOs = productMapper.toDto(products);
		
		LogUtil.exitMethod(logger, getClassName(), "getProductsBySku(sku, pageNum, pageSize, sortBy, desc)", products);
		return productDTOs;
	}

	@Override
	public Page<ProductDTO> getProductsByName(String name, int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", name, pageNum, pageSize, sortBy, desc);

		Page<Product> products = productRepository.findActiveByName(name, ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		Page<ProductDTO> productDTOs = productMapper.toDto(products);
		
		LogUtil.exitMethod(logger, getClassName(), "getProductsByName(name, pageNum, pageSize, sortBy, desc)", products);
		return productDTOs;
	}

	@Override
	public ProductDTO getProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductsById(id)", id);

		Optional<Product> productOpt = productRepository.findById(id);
		Product product = productOpt.isPresent() ? productOpt.get() : null;
		ProductDTO productDTO = productMapper.toDto(product);

		LogUtil.exitMethod(logger, getClassName(), "getProductsById(id)", product);
		return productDTO;
	}

	@Override
	public ProductDTO saveProduct(ProductDTO product) {
		LogUtil.enterMethod(logger, getClassName(), "saveProduct(product)", product);

		Product productSaved = productRepository.save(productMapper.toDomain(product));
		ProductDTO productSavedDTO = productMapper.toDto(productSaved);
		
		LogUtil.exitMethod(logger, getClassName(), "saveProduct(product)", productSaved);
		return productSavedDTO;
	}

	@Override
	public void deleteProduct(ProductDTO product) {
		LogUtil.enterMethod(logger, getClassName(), "deleteProduct(product)", product);
		productRepository.delete(productMapper.toDomain(product));
		LogUtil.exitMethod(logger, getClassName(), "deleteProduct(product)");
	}

	@Override
	public void deleteProductById(long id) {
		LogUtil.enterMethod(logger, getClassName(), "deleteProductById(id)", id);
		productRepository.deleteById(id);
		LogUtil.exitMethod(logger, getClassName(), "deleteProductById(id)");
	}
	
	@Override
	public Page<ProductStatusDTO> getProductStatuses(int pageNum, int pageSize, String sortBy, boolean desc) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatuses(pageNum, pageSize, sortBy, desc)", pageNum, pageSize, sortBy, desc);
		
		Page<ProductStatus> statuses = productStatusRepository.findAll(ServiceUtil.getPageable(pageNum, pageSize, sortBy, desc, Product.SORT_BY));
		Page<ProductStatusDTO> statusDTOs = productStatusMapper.toDto(statuses);

		LogUtil.exitMethod(logger, getClassName(), "getProductStatuses()", statuses);
		return statusDTOs;
	}
	
	@Override
	public ProductStatusDTO getProductStatus(long id) {
		LogUtil.enterMethod(logger, getClassName(), "getProductStatus(id)", id);
		
		Optional<ProductStatus> productStatustOpt = productStatusRepository.findById(id);
		ProductStatus status = productStatustOpt.isPresent() ? productStatustOpt.get() : null;
		ProductStatusDTO statusDTO = productStatusMapper.toDto(status);

		LogUtil.exitMethod(logger, getClassName(), "getProductStatus(id)", status);
		return statusDTO;
	}
	
	private String getClassName() {
		return ProductServiceImpl.class.getName();
	}
}
