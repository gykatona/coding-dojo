package com.dojo.codingdojo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class FtpConfiguration {

    @Bean
    DefaultFtpSessionFactory defaultFtpSessionFactory(
            @Value("${ftp.username}") String username, @Value("${ftp.password}") String password,
            @Value("${ftp.host}") String host, @Value("${ftp.port}") int port
    ) {
        DefaultFtpSessionFactory session = new DefaultFtpSessionFactory();
        session.setUsername(username);
        session.setPassword(password);
        session.setHost(host);
        session.setPort(port);
        return session;
    }
}
