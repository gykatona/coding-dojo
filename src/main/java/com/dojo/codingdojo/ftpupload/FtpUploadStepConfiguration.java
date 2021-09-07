package com.dojo.codingdojo.ftpupload;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FtpUploadStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final FtpUploadTasklet ftpUploadTasklet;

    @Bean
    public Step ftpUploadStep() {
        return stepBuilderFactory
                .get("remoteLoadStep")
                .tasklet(ftpUploadTasklet)
                .build();
    }
}
