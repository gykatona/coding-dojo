package com.dojo.codingdojo.remotedump.partitioner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiFileWriterPartitionerConfiguration {

    @Bean
    public MultiFileWriterPartitioner partitioner() {
        return new MultiFileWriterPartitioner();
    }
}
