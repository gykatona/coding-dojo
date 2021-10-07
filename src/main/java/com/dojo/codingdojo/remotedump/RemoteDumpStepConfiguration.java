package com.dojo.codingdojo.remotedump;

import com.dojo.codingdojo.pojo.Source;
import com.dojo.codingdojo.remotedump.partitioner.MultiFileWriterPartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class RemoteDumpStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final FlatFileItemWriter<Source> remoteDumpWriter;
    private final JdbcPagingItemReader<Source> remoteDumpReader;
    private final MultiFileWriterPartitioner multiFileWriterPartitioner;

    @Bean
    public Step partitionRemoteDumpStep() {
        return stepBuilderFactory.get("partitionStep")
                .partitioner("slaveStep", multiFileWriterPartitioner)
                .step(remoteDumpStep())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(5);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

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
