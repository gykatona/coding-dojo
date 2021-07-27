package com.dojo.codingdojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final Step transformPersonStep;

    public JobConfiguration(JobBuilderFactory jobBuilderFactory, Step transformPersonStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.transformPersonStep = transformPersonStep;
    }

    @Bean
    protected Job ebayDownloadOrderJob() {
        return jobBuilderFactory
                .get("coding-dojo")
                .incrementer(new RunIdIncrementer())
                .start(transformPersonStep)
                .build();
    }
}
