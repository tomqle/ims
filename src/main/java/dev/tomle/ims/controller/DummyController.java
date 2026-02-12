package dev.tomle.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.model.security.User;
import dev.tomle.ims.model.security.dto.UserDTO;
import dev.tomle.ims.util.RestControllerUtil;
import dev.tomle.ims.util.ServiceUtil;

@RestController
@RequestMapping("/dummy")
public class DummyController {

	@GetMapping
	public PagedModel<UserDTO> getDummy(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc,
													@RequestParam(required = false) String sku) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);

		
		List<UserDTO> users = new ArrayList<>();
		int totalElements = 100;
		int start = validPageNumber * validPageSize;
		int end = start + validPageSize;
		for(int i = start; i < end; i++) {
			UserDTO u1 = new UserDTO();
			int id = i + 1;
			u1.id = id;
			u1.setUsername("user" + id);
			u1.setFirstName("first");
			u1.setLastName("last");
			u1.setEmail("user" + id + "@test.com");
			users.add(u1);
		}
		Page<UserDTO> page = new PageImpl<>(users, ServiceUtil.getPageable(validPageNumber, validPageSize, validSortBy, validDesc, User.SORT_BY), totalElements);
		PagedModel<UserDTO> pagedResponse = new PagedModel<>(page);

		return pagedResponse;
	}

}
