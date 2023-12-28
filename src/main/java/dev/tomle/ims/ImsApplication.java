package dev.tomle.ims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SecurityScheme(name = "openapi", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ImsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(ImsApplication.class);

	public static void main(String[] args) {
		logger.debug("ImsApplication started");
		SpringApplication.run(ImsApplication.class, args);
	}
}
