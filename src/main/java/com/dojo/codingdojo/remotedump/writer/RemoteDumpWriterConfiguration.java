package com.dojo.codingdojo.remotedump.writer;

import com.dojo.codingdojo.pojo.Source;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;

@Configuration
public class RemoteDumpWriterConfiguration {
    @Value("${remote.file}")
    private String fileName;

    @Bean
    @StepScope
    public FlatFileItemWriter<Source> remoteDumpWriter() {
        return new FlatFileItemWriterBuilder<Source>()
                .name("remoteDumpWriter")
                .resource(new FileSystemResource(fileName))
                .delimited()
                .delimiter(DELIMITER_TAB)
                .names("firstName", "lastName", "age")
                .build();
    }
}
