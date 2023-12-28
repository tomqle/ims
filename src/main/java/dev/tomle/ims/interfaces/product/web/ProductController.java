package dev.tomle.ims.interfaces.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.interfaces.product.facade.ProductServiceFacade;
import dev.tomle.ims.interfaces.product.facade.dto.ProductDTO;
import dev.tomle.ims.interfaces.product.facade.dto.ProductStatusDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
public final class ProductController {
	
	@Autowired
	private ProductServiceFacade productServiceFacade;
	
	@GetMapping(path="/product")
	public Page<ProductDTO> getProducts(@RequestParam(required = false) Integer pageNumber,
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
			return productServiceFacade.getProductsBySku(sku, validPageNumber, validPageSize, validSortBy, validDesc);
		} else if (name != null) {
			return productServiceFacade.getProductsByName(name, validPageNumber, validPageSize, validSortBy, validDesc);
		}
		return productServiceFacade.getProducts(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "/product/{id}")
	public ProductDTO getProduct(@PathVariable long id) {
		return productServiceFacade.getProductById(id);
	}

	@PostMapping(path = "/product")
	public ProductDTO postProduct(@RequestBody ProductDTO productDTO) {
		return productServiceFacade.saveProduct(productDTO);
	}

	@PutMapping(path = "/product/{id}")
	public ProductDTO putProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {
		productDTO.setId(id);
		return productServiceFacade.saveProduct(productDTO);
	}
	
	@DeleteMapping(path = "/product/{id}")
	public void deleteProduct(@PathVariable long id) {
		productServiceFacade.deleteProductById(id);
	}
	
	@GetMapping(path="/productStatus")
	public Page<ProductStatusDTO> getProductStatuses(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);
		return productServiceFacade.getProductStatuses(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path="/productStatus/{id}")
	public ProductStatusDTO getProductStatus(@PathVariable long id) {
		return productServiceFacade.getProductStatus(id);
	}
}
