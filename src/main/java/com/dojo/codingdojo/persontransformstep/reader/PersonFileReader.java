package com.dojo.codingdojo.persontransformstep.reader;

import com.dojo.codingdojo.pojo.Person;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PersonFileReader {

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("feladat_1.csv"))
                .lineMapper(provideLineMapper())
                .build();
    }

    private DelimitedLineTokenizer provideTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
        tokenizer.setNames(new String[]{"lastName", "firstName", "age"});
        return tokenizer;
    }

    private DefaultLineMapper<Person> provideLineMapper() {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(provideTokenizer());
        lineMapper.setFieldSetMapper(provideFieldSetMapper());
        return lineMapper;
    }

    private BeanWrapperFieldSetMapper<Person> provideFieldSetMapper() {
        BeanWrapperFieldSetMapper<Person> personBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        personBeanWrapperFieldSetMapper.setTargetType(Person.class);
        return personBeanWrapperFieldSetMapper;
    }
}
