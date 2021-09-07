package com.dojo.codingdojo.ftp;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FtpActions {
    private final DefaultFtpSessionFactory defaultFtpSessionFactory;

    public void upload(Resource resource) throws IOException {
        FtpSession session = defaultFtpSessionFactory.getSession();
        session.write(resource.getInputStream(), "mynewfile.txt");
    }

}
