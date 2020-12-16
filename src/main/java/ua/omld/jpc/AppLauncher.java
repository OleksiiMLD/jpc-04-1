package ua.omld.jpc;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.omld.jpc.configuration.BasicConfiguration;
import ua.omld.jpc.ws.ServicePublisher;

/**
 * @author Oleksii Kostetskyi
 */
public class AppLauncher {

	public void start() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BasicConfiguration.class);
		ServicePublisher publisher = context.getBean(ServicePublisher.class);
		publisher.publishReportService();
		publisher.publishActivityService();
		publisher.publishBuildingService();
	}
}
