package dev.tomle.ims.model.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.model.ProductStatus;
import dev.tomle.ims.model.dto.ProductStatusDTO;

@Component
public class ProductStatusMapper implements BaseEntityMapper<ProductStatus, ProductStatusDTO> {

	@Override
	public ProductStatusDTO toDto(ProductStatus productStatus) {
		ProductStatusDTO productStatusDTO = null;
		if(productStatus != null) {
			productStatusDTO = new ProductStatusDTO();
			setBaseEntityMembers(productStatus, productStatusDTO);
			productStatusDTO.status = productStatus.getStatus();
		}

		return productStatusDTO;
	}

	@Override
	public ProductStatus toDomain(ProductStatusDTO productStatusDTO) {
		ProductStatus productStatus = null;
		if(productStatusDTO != null) {
			productStatus = new ProductStatus();
			setBaseEntityMembers(productStatusDTO, productStatus);
			productStatus.setStatus(productStatusDTO.status);
		}
		return productStatus;
	}

}
