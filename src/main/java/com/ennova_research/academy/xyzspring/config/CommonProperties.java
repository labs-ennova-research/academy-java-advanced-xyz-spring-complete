package com.ennova_research.academy.xyzspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alberto Ielpo
 */
@Configuration
@PropertySource("classpath:common.properties")
public class CommonProperties {

	@Value("${url.api}")
	public String urlApi;
	
	@Value("${fake.user.url}")
	public String fakeUserUrl;
	
	@Value("${fake.user.endpoint}")
	public String fakeUserEndpoint;
	
}
