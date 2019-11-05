package io.garden.project.model.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 4734446097399024898L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
