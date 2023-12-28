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

import dev.tomle.ims.interfaces.security.facade.PrivilegeServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.PrivilegeDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
@RequestMapping("/privilege")
public final class PrivilegeController {

	@Autowired
	private PrivilegeServiceFacade privilegeServiceFacade;
	
	@GetMapping
	public Page<PrivilegeDTO> getPrivileges(@RequestParam(required = false) Integer pageNumber,
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
			return privilegeServiceFacade.getPrivilegesByName(validPageNumber, validPageSize, validSortBy, validDesc, name);
		}
		return privilegeServiceFacade.getPrivileges(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "/{id}")
	public PrivilegeDTO getPrivilege(@PathVariable long id) {
		return privilegeServiceFacade.getPrivilegeById(id);
	}
	
	@PostMapping
	public PrivilegeDTO postPrivilege(@RequestBody PrivilegeDTO privilegeDTO) {
		return privilegeServiceFacade.savePrivilege(privilegeDTO);
	}

	@PutMapping(path = "/{id}")
	public PrivilegeDTO putPrivilege(@PathVariable long id, @RequestBody PrivilegeDTO privilegeDTO) {
		privilegeDTO.setId(id);
		return privilegeServiceFacade.savePrivilege(privilegeDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deletePrivilege(@PathVariable long id) {
		privilegeServiceFacade.deletePrivilegeById(id);
	}
}
