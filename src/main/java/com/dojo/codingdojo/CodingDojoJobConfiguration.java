package com.dojo.codingdojo;

import com.dojo.codingdojo.common.CustomExitStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class CodingDojoJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final Step personFileWriterStep;
    private final Step transformPersonStep;
    private final Step remoteDumpStep;
    private final Step remoteLoadStep;
    private final Step fetchMarketplaceStep;
    private final Step fetchStatusStep;
    private final Step ftpUploadStep;

    @Bean
    protected Job codingDojoJob() {
        return jobBuilderFactory
                .get("coding-dojo")
                .incrementer(new RunIdIncrementer())
                .start(remoteDumpStep)
                .next(remoteLoadStep)
                .on(CustomExitStatus.FTP.getExitCode()).to(ftpUploadStep).next(remainingSteps()).from(remoteLoadStep)
                .on(ExitStatus.COMPLETED.getExitCode()).to(remainingSteps()).from(remoteLoadStep)
                .end()
                .build();
    }

    @Bean
    public Flow remainingSteps() {
        return new FlowBuilder<SimpleFlow>("smart-ftp-flow")
                .start(personFileWriterStep)
                .next(transformPersonStep)
                .next(fetchMarketplaceStep)
                .next(fetchStatusStep)
                .end();
    }
}
