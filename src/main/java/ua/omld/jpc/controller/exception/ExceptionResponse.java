package ua.omld.jpc.controller.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Response format for Exceptions
 *
 * @author Oleksii Kostetskyi
 */
public class ExceptionResponse {

	private int statusCode;
	private String message;
	private String path;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	public ExceptionResponse(HttpStatus status, String path, String message) {
		this.statusCode = status.value();
		this.timestamp = LocalDateTime.now();
		this.path = path;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getPath() {
		return path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}
}
