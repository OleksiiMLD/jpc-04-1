package ua.omld.jpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import ua.omld.jpc.configuration.BasicConfiguration;
import ua.omld.jpc.configuration.WebStartCondition;
import ua.omld.jpc.ws.ServicePublisher;

/**
 * @author Oleksii Kostetskyi
 */
public class AppLauncher {

	public void start() {
		GenericApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles(WebStartCondition.LOCAL);
		context.registerBean(BasicConfiguration.class );
		context.refresh();
		ServicePublisher publisher = context.getBean(ServicePublisher.class);
		publisher.publishReportService();
		publisher.publishActivityService();
		publisher.publishBuildingService();
	}
}
