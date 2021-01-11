package ua.omld.jpc.controller.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.omld.jpc.configuration.WebStartCondition;
import ua.omld.jpc.exception.DAOException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Oleksii Kostetskyi
 */
@ResponseBody
@ControllerAdvice(basePackages = "ua.omld.jpc.controller")
@Conditional(WebStartCondition.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionsHandler {

	private static final Logger LOGGER = LogManager.getLogger();

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
		String uri = getFullUri(request);
		return new ExceptionResponse(HttpStatus.NOT_FOUND, uri, e.getMessage());
	}

	@ExceptionHandler(DAOException.class)
	@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
	public ExceptionResponse datasourceError(Exception e, HttpServletRequest request) {
		String uri = getFullUri(request);
		return new ExceptionResponse(HttpStatus.INSUFFICIENT_STORAGE, uri, "Data storage error.");
	}

	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse authenticationError(HttpServletRequest request) {
		String uri = getFullUri(request);
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED, uri, "Not authenticated! Please provide credentials.");
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse authorizationError(HttpServletRequest request) {
		String uri = getFullUri(request);
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED, uri, "Access denied!");
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse generalError(Exception e, HttpServletRequest request) {
		LOGGER.error("Unknown Exception!", e);
		String uri = getFullUri(request);
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, uri,
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	}

	private String getFullUri(HttpServletRequest request) {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();
		return (queryString == null) ? requestURL.toString() : requestURL.append('?').append(queryString).toString();
	}
}
