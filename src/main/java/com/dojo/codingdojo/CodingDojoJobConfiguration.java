package com.dojo.codingdojo;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
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
                .next(personFileWriterStep)
                .next(transformPersonStep)
                .next(fetchMarketplaceStep)
                .next(fetchStatusStep)
                .next(ftpUploadStep)
                .build();
    }
}
