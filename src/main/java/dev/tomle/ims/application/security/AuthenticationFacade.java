package dev.tomle.ims.application.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	public Authentication getAuthentication();
}
