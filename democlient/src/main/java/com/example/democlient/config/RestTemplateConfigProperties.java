package com.example.democlient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfigProperties {

    @Value("${demo.server.path}")
    private String serverPath;

    @Value("${demo.server.port}")
    private Integer port;

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUrl() {
        return this.serverPath + ":" + this.port;
    }
}
