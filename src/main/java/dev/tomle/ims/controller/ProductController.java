package dev.tomle.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.model.dto.ProductDTO;
import dev.tomle.ims.model.dto.ProductStatusDTO;
import dev.tomle.ims.service.ProductService;
import dev.tomle.ims.util.RestControllerUtil;

@RestController
@RequestMapping("/product")
public final class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public PagedModel<ProductDTO> getProducts(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc,
													@RequestParam(required = false) String sku,
													@RequestParam(required = false) String name) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		if(sku != null) {
			return new PagedModel<>(productService.getProductsBySku(sku, validPageNumber, validPageSize, validSortBy, validDesc));
		} else if (name != null) {
			return new PagedModel<>(productService.getProductsByName(name, validPageNumber, validPageSize, validSortBy, validDesc));
		}
		return new PagedModel<>(productService.getProducts(validPageNumber, validPageSize, validSortBy, validDesc));
	}

	@GetMapping(path = "/{id}")
	public ProductDTO getProduct(@PathVariable long id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public ProductDTO postProduct(@RequestBody ProductDTO productDTO) {
		return productService.saveProduct(productDTO);
	}

	@PutMapping(path = "/{id}")
	public ProductDTO putProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {
		productDTO.id = id;
		return productService.saveProduct(productDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteProduct(@PathVariable long id) {
		productService.deleteProductById(id);
	}
	
	@GetMapping(path="/status")
	public PagedModel<ProductStatusDTO> getProductStatuses(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return new PagedModel<>(productService.getProductStatuses(validPageNumber, validPageSize, validSortBy, validDesc));
	}

	@GetMapping(path="/status/{id}")
	public ProductStatusDTO getProductStatus(@PathVariable long id) {
		return productService.getProductStatus(id);
	}
}
