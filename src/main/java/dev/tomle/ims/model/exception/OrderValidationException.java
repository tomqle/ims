package dev.tomle.ims.model.exception;

import java.io.Serial;

public class OrderValidationException extends Exception {

	public OrderValidationException(String string) {
		super(string);
	}

    @Serial
    private static final long serialVersionUID = 1L;

}
