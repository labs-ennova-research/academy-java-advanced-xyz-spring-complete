package com.ennova_research.academy.xyzspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alberto Ielpo
 */
@ComponentScan("com.ennova_research.academy.xyzspring")
public class AppConfig {

	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
