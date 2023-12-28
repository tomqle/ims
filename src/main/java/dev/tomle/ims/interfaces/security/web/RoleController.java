package dev.tomle.ims.interfaces.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.interfaces.security.facade.RoleServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.RoleDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
@RequestMapping("/role")
public final class RoleController {

	@Autowired
	private RoleServiceFacade roleServiceFacade;
	
	@GetMapping
	public Page<RoleDTO> getRoles(@RequestParam(required = false) Integer pageNumber,
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
			return roleServiceFacade.getRolesByName(validPageNumber, validPageSize, validSortBy, validDesc, name);
		}
		return roleServiceFacade.getRoles(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "/{id}")
	public RoleDTO getRole(@PathVariable long id) {
		return roleServiceFacade.getRoleById(id);
	}
	
	@PostMapping
	public RoleDTO postRole(@RequestBody RoleDTO roleDTO) {
		return roleServiceFacade.saveRole(roleDTO);
	}

	@PutMapping(path = "/{id}")
	public RoleDTO putRole(@PathVariable long id, @RequestBody RoleDTO roleDTO) {
		roleDTO.setId(id);
		return roleServiceFacade.saveRole(roleDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteRole(@PathVariable long id) {
		roleServiceFacade.deleteRoleById(id);
	}
}
