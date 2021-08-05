package com.dojo.codingdojo.persontransformstep;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.persontransformstep.processor.PersonProcessor;
import com.dojo.codingdojo.persontransformstep.writer.PersonWriter;
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
    private final PersonWriter writer;

    public PersonTransformStepConfiguration(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<Person> reader, PersonProcessor processor, PersonWriter writer) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Bean
    public Step transformPersonStep() {
        return stepBuilderFactory
                .get("Transform person")
                .<Person, Person>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
