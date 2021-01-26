package ua.omld.jpc.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

/**
 * @author Oleksii Kostetskyi
 */
public class ConstraintViolationsResponse extends ExceptionResponse {

	private MultiValueMap<String, String> violations;

	public ConstraintViolationsResponse(HttpStatus status, String path, String message, MultiValueMap<String, String> violations) {
		super(status, path, message);
		this.violations = violations;
	}

	public MultiValueMap<String, String> getViolations() {
		return violations;
	}
}
