package dev.tomle.ims.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductServiceImpl productService;
	
	@Test
	public void productRepositoryTest() {
		//Product product = productRepository.findBySku("USB-DRIVE-8GB");
		//ProductStatus status = product.getProductStatus();
		
		//assertEquals(product.getId(), 1);
	}
}
