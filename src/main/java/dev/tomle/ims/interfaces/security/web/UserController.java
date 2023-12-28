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

import dev.tomle.ims.interfaces.security.facade.UserServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.UserDTO;
import dev.tomle.ims.interfaces.util.RestControllerUtil;

@RestController
@RequestMapping("/user")
public final class UserController {

	@Autowired
	private UserServiceFacade userServiceFacade;
	
	@GetMapping
	public Page<UserDTO> getUsers(@RequestParam(required = false) Integer pageNumber,
													@RequestParam(required = false) Integer pageSize,
													@RequestParam(required = false) String sortBy,
													@RequestParam(required = false) Boolean desc,
													@RequestParam(required = false) String sku,
													@RequestParam(required = false) String username) {
		int validPageNumber = RestControllerUtil.getValidatedPageNumber(pageNumber);
		int validPageSize = RestControllerUtil.getValidatedPageSize(pageSize);
		String validSortBy = RestControllerUtil.getValidatedSortyBy(sortBy);
		boolean validDesc = RestControllerUtil.getValidatedDesc(desc);

		if (username != null) {
			return userServiceFacade.getUsersByUsername(validPageNumber, validPageSize, validSortBy, validDesc, username);
		}
		return userServiceFacade.getUsers(validPageNumber, validPageSize, validSortBy, validDesc);
	}

	@GetMapping(path = "/{id}")
	public UserDTO getUser(@PathVariable long id) {
		return userServiceFacade.getUserById(id);
	}
	
	@PostMapping
	public UserDTO postUser(@RequestBody UserDTO userDTO) {
		return userServiceFacade.saveUser(userDTO);
	}

	@PutMapping(path = "/{id}")
	public UserDTO putUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
		return userServiceFacade.saveUser(userDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable long id) {
		userServiceFacade.deleteUserById(id);
	}
}
