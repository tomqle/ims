package dev.tomle.ims.application.order;

public class OrderServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public OrderServiceException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

	public OrderServiceException(String errorMessage) {
		super(errorMessage);
	}
}
