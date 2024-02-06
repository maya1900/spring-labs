package com.maya;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@EnableConfigurationProperties(mayaProperties.class)
public class mayaConfiguration {

    private Logger logger = LoggerFactory.getLogger(mayaConfiguration.class);

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(mayaProperties properties) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(properties.getPort()), 0);
        server.start();
        logger.info("Server started at port {}", properties.getPort());
        return server;
    }
}
