package dev.tomle.ims.interfaces.product.facade.dto.mapper;

import org.springframework.stereotype.Component;

import dev.tomle.ims.domain.model.product.ProductStatus;
import dev.tomle.ims.interfaces.product.facade.dto.ProductStatusDTO;
import dev.tomle.ims.interfaces.shared.dto.BaseEntityMapper;

@Component
public class ProductStatusMapper implements BaseEntityMapper<ProductStatus, ProductStatusDTO> {

	@Override
	public ProductStatusDTO toDto(ProductStatus productStatus) {
		ProductStatusDTO productStatusDTO = null;
		if(productStatus != null) {
			productStatusDTO = new ProductStatusDTO();
			setBaseEntityMembers(productStatus, productStatusDTO);
			productStatusDTO.setStatus(productStatus.getStatus());
		}

		return productStatusDTO;
	}

	@Override
	public ProductStatus toDomain(ProductStatusDTO productStatusDTO) {
		ProductStatus productStatus = null;
		if(productStatusDTO != null) {
			productStatus = new ProductStatus();
			setBaseEntityMembers(productStatusDTO, productStatus);
			productStatus.setStatus(productStatusDTO.getStatus());
		}
		return productStatus;
	}

}
