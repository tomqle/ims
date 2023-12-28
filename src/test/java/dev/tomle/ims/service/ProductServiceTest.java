package dev.tomle.ims.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.tomle.ims.application.product.impl.ProductServiceImpl;
import dev.tomle.ims.domain.model.product.Product;
import dev.tomle.ims.infrastructure.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
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
