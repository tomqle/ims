package dev.tomle.ims.service;

import java.io.Serial;

public class OrderServiceException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
	
	public OrderServiceException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

	public OrderServiceException(String errorMessage) {
		super(errorMessage);
	}
}
