package dev.tomle.ims.infrastructure.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

import dev.tomle.ims.application.security.AuthenticationFacade;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Autowired
	AuthenticationFacade authenticationFacade;

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = authenticationFacade.getAuthentication();
		String username = authentication != null ? authenticationFacade.getAuthentication().getName() : "";
		return Optional.of(username);
	}
}
