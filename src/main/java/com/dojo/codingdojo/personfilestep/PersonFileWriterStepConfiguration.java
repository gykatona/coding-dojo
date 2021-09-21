package com.dojo.codingdojo.personfilestep;

import com.dojo.codingdojo.personfilestep.reader.PersonFetcher;
import com.dojo.codingdojo.pojo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersonFileWriterStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final PersonFetcher personFetcher;
    private final FlatFileItemWriter<Person> personFlatFileWriter;

    @Bean
    public Step personFileWriterStep() {
        return stepBuilderFactory
                .get("personFileWriterStep")
                .<Person, Person>chunk(100)
                .reader(personFetcher)
                .writer(personFlatFileWriter)
                .build();
    }
}
