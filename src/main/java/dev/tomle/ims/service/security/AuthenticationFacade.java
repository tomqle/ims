package dev.tomle.ims.service.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	public Authentication getAuthentication();
}
