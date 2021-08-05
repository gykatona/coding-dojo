package com.dojo.codingdojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CodingDojoJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final Step personFileWriterStep;
    private final Step transformPersonStep;

    public CodingDojoJobConfiguration(JobBuilderFactory jobBuilderFactory, Step personFileWriterStep, Step transformPersonStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.personFileWriterStep = personFileWriterStep;
        this.transformPersonStep = transformPersonStep;
    }

    @Bean
    protected Job codingDojoJob() {
        return jobBuilderFactory
                .get("coding-dojo")
                .incrementer(new RunIdIncrementer())
                .start(personFileWriterStep)
                .next(transformPersonStep)
                .build();
    }
}
