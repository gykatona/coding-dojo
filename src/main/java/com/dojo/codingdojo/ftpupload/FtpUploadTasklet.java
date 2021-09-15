package com.dojo.codingdojo.ftpupload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class FtpUploadTasklet implements Tasklet {
    private final FTPClient ftpClient;

    @Value("${remote.file}")
    private String fileName;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private int port;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            log.info("Connecting to ftp server ...");
            ftpClient.connect(host, port);
            boolean connected = ftpClient.login(username, password);
            log.info("Is connected {}", connected);
            ftpClient.enterLocalPassiveMode();
            log.info("Entered local passive mode.");
            FTPFile[] files = ftpClient.listFiles();
            log.debug("Found {} files.", files.length);
            boolean stored = ftpClient.storeFile("ftp.txt", new FileInputStream(fileName));
            log.info("Did store file {}", stored);
            boolean loggedOut = ftpClient.logout();
            log.info("Is logged out {}", loggedOut);
        } finally {
            ftpClient.disconnect();
        }

        return RepeatStatus.FINISHED;
    }
}
