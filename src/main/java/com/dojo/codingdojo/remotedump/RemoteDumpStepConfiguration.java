package com.dojo.codingdojo.remotedump;

import com.dojo.codingdojo.pojo.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RemoteDumpStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final FlatFileItemWriter<Source> remoteDumpWriter;
    private final JdbcPagingItemReader<Source> remoteDumpReader;

    @Bean
    public Step remoteDumpStep() {
        return stepBuilderFactory
                .get("remoteDumpStep")
                .<Source, Source>chunk(100)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .reader(remoteDumpReader)
                .writer(remoteDumpWriter)
                .build();
    }
}
