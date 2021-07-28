package com.dojo.codingdojo.reader;

import com.dojo.codingdojo.pojo.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PersonReader {

    @Value("${csv.fileName}")
    private String fileName;

    @Value("${csv.fields}")
    private String[] fields;

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource(fileName))
                .lineMapper(provideLineMapper())
                .linesToSkip(1)
                .build();
    }

    private DelimitedLineTokenizer provideTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
        tokenizer.setNames(fields);
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
