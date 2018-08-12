package com.example.democlient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final RestTemplateConfigProperties restTemplateConfigProperties;

    @Autowired
    public RestTemplateConfig(RestTemplateConfigProperties restTemplateConfigProperties) {
        this.restTemplateConfigProperties = restTemplateConfigProperties;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(restTemplateConfigProperties.getUrl()).build();
    }
}
