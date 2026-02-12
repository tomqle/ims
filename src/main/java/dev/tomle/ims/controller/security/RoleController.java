package dev.tomle.ims.controller.security;

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

import dev.tomle.ims.model.security.dto.RoleDTO;
import dev.tomle.ims.service.security.RoleService;
import dev.tomle.ims.util.RestControllerUtil;

@RestController
@RequestMapping("/role")
public final class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public PagedModel<RoleDTO> getRoles(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc,
													@RequestParam(required = false) String sku,
													@RequestParam(required = false) String name) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);

		if (name != null) {
			return new PagedModel<>(roleService.getRolesByName(validPageNumber, validPageSize, validSortBy, validDesc, name));
		}
		return new PagedModel<>(roleService.getRoles(validPageNumber, validPageSize, validSortBy, validDesc));
	}

	@GetMapping(path = "/{id}")
	public RoleDTO getRole(@PathVariable long id) {
		return roleService.getRoleById(id);
	}
	
	@PostMapping
	public RoleDTO postRole(@RequestBody RoleDTO roleDTO) {
		return roleService.saveRole(roleDTO);
	}

	@PutMapping(path = "/{id}")
	public RoleDTO putRole(@PathVariable long id, @RequestBody RoleDTO roleDTO) {
		roleDTO.id = id;
		return roleService.saveRole(roleDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteRole(@PathVariable long id) {
		roleService.deleteRoleById(id);
	}
}
