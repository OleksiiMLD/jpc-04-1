package ua.omld.jpc.configuration;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Oleksii Kostetskyi
 */
@Configuration
@Conditional(WebStartCondition.class)
@EnableWebMvc
@ImportResource("/WEB-INF/web-services.xml")
public class WebConfig {

	public static final String REST_COMMON_PREFIX = "/api/";
}