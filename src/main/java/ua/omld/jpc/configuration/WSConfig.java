package ua.omld.jpc.configuration;

import jpc.omld.ua.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oleksii Kostetskyi
 */
@Configuration
public class WSConfig {

	@Bean
	public ObjectFactory ObjectFactory() {
		return new ObjectFactory();
	}
}
