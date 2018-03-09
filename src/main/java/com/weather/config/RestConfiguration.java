package com.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 注入RestTemplate
 * @author yyh
 * @date 2018/3/8 16:04
 */
@Configuration
public class RestConfiguration {
	private final RestTemplateBuilder builder;

	@Autowired
	public RestConfiguration(RestTemplateBuilder builder) {
		this.builder = builder;
	}

	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}
}
