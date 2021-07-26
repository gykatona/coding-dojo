package com.dojo.codingdojo;

import com.dojo.codingdojo.pojo.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonTransformStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final FlatFileItemReader<Person> reader;
    private final PersonProcessor processor;

    public PersonTransformStepConfiguration(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<Person> reader, PersonProcessor processor) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.reader = reader;
        this.processor = processor;
    }

    @Bean
    public Step transformPersonStep() {
        return stepBuilderFactory
                .get("Transform person")
                .<Person, Person>chunk(1)
                .reader(reader)
                .processor(processor)
//                .writer()
                .build();
    }

}
