package dev.tomle.ims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ImsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(ImsApplication.class);

	public static void main(String[] args) {
		logger.debug("ImsApplication started");
		SpringApplication.run(ImsApplication.class, args);
	}
}
