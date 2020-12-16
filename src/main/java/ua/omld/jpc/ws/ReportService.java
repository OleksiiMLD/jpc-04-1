package ua.omld.jpc.ws;

import jpc.omld.ua.UserReportsRequest;
import jpc.omld.ua.UserReportsResponse;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Provides Web-Service interface for {@link Report}
 *
 * @author Oleksii Kostetskyi
 */
@SOAPBinding
@WebService(name = "ReportService", serviceName = "JPCReportService", portName = "JPCReportServicePort")
public interface ReportService {

	/**
	 * Returns {@link UserReportsResponse} with a list of all {@link jpc.omld.ua.Report Reports}
	 * for the {@link User} with the given id
	 *
	 * @param request id of user for witch search reports
	 * @return list of user's reports
	 */
	@WebMethod(operationName = "GetUserReports")
	@WebResult(name = "GetUserReportsResponseAttributes")
	UserReportsResponse findAllByUserId(@WebParam(name = "GetUserReportsRequestAttributes") UserReportsRequest request);
}
