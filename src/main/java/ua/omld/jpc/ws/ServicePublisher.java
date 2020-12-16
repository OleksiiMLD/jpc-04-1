package ua.omld.jpc.ws;

/**
 * Provides interface for publishing Web Services.
 *
 * @author Oleksii Kostetskyi
 */
public interface ServicePublisher {

	void publishReportService();

	void publishActivityService();

	void publishBuildingService();
}
