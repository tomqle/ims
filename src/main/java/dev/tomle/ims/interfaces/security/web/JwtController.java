package dev.tomle.ims.interfaces.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tomle.ims.infrastructure.security.repository.UserRepository;
import dev.tomle.ims.interfaces.security.facade.UserServiceFacade;
import dev.tomle.ims.interfaces.security.facade.dto.JwtRequestDTO;
import dev.tomle.ims.interfaces.security.facade.dto.JwtResponseDTO;
import dev.tomle.ims.interfaces.security.facade.dto.UserDTO;
import dev.tomle.ims.interfaces.security.facade.dto.mapper.UserMapper;

@RestController
@RequestMapping("/auth")
public final class JwtController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServiceFacade userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;


	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> createToken(@RequestBody JwtRequestDTO request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch(DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwtToken = userService.login(request.getUsername());
		final UserDTO userDTO = userMapper.toDto(userRepository.findByUsername(request.getUsername()));
		return ResponseEntity.ok(new JwtResponseDTO(jwtToken, userDTO));
	}
}
