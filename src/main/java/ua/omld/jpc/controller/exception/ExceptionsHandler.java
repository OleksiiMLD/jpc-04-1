package ua.omld.jpc.controller.exception;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
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

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse generalError(Exception e, HttpServletRequest request) {
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
