package com.maya;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "maya.server")
public class mayaProperties {

    private static final Integer DEFAULT_PORT = 8080;

    private Integer port = DEFAULT_PORT;

    public Integer getPort() {
        return port;
    }

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public mayaProperties setPort(Integer port) {
        this.port = port;
        return this;
    }
}
