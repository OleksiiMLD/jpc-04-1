package ua.omld.jpc.ws;

import jpc.omld.ua.CommonInterfaceResponse;
import jpc.omld.ua.Error;
import jpc.omld.ua.Failure;
import jpc.omld.ua.ObjectFactory;

/**
 * @author Oleksii Kostetskyi
 */
public class ResponseUtil {

	private static final String FAILURE = "FAILURE";
	private static final String FUNCTIONAL = "FUNCTIONAL";
	private static final String TECHNICAL = "TECHNICAL";

	public static void setFunctionalFailure(CommonInterfaceResponse response, Exception e, ObjectFactory objectFactory) {
		setFailure(response, e, objectFactory, FUNCTIONAL);
	}

	public static void setTechnicalFailure(CommonInterfaceResponse response, Exception e, ObjectFactory objectFactory) {
		setFailure(response, e, objectFactory, TECHNICAL);
	}

	public static void setFailure(CommonInterfaceResponse response, Exception e, ObjectFactory objectFactory, String errorType) {
		response.setStatus(FAILURE);
		Error error = objectFactory.createError();
		error.setErrorType(errorType);
		error.setErrorDescription(e.getMessage());
		Failure failure = objectFactory.createFailure();
		failure.getError().add(error);
		response.setFailure(objectFactory.createCommonInterfaceResponseFailure(failure));
	}
}
