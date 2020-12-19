package ua.omld.jpc.configuration;

import jpc.omld.ua.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.omld.jpc.ws.ServicePublisher;

/**
 * @author Oleksii Kostetskyi
 */
@Configuration
public class WSConfig {

	@Value("${ws.default.uri}")
	private String wsDefaultUri;

	@Bean
	public String getWsDefaultUri() {
		return wsDefaultUri;
	}

	@Bean
	public ObjectFactory ObjectFactory() {
		return new ObjectFactory();
	}

	@Autowired
	public void publishJaxWsServices(ServicePublisher servicePublisher) {
		servicePublisher.publishActivityService();
		servicePublisher.publishBuildingService();
		servicePublisher.publishReportService();
	}
}
