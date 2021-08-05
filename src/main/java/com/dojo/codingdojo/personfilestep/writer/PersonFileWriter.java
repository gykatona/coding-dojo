package com.dojo.codingdojo.personfilestep.writer;

import com.dojo.codingdojo.pojo.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PersonFileWriter {

    private final Resource outputResource = new FileSystemResource("src/main/resources/feladat_1.csv");

    @Bean
    public FlatFileItemWriter<Person> personFlatFileWriter() {
        FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        writer.setShouldDeleteIfExists(true);
        writer.setLineAggregator(provideDelimitedLineAggregator());
        return writer;
    }

    private DelimitedLineAggregator<Person> provideDelimitedLineAggregator() {
        DelimitedLineAggregator<Person> personDelimitedLineAggregator = new DelimitedLineAggregator<>();
        personDelimitedLineAggregator.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
        personDelimitedLineAggregator.setFieldExtractor(providePersonBeanWrapperFieldExtractor());

        return personDelimitedLineAggregator;
    }

    private BeanWrapperFieldExtractor<Person> providePersonBeanWrapperFieldExtractor() {
        BeanWrapperFieldExtractor<Person> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"lastName", "firstName", "age"});

        return beanWrapperFieldExtractor;
    }

}
