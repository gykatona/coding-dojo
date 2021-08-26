package com.dojo.codingdojo.persontransformstep.writer;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonWriter implements ItemWriter<Person> {

    private final PersonRepository personRepository;

    @Override
    public void write(List<? extends Person> personList) throws Exception {
        personList.forEach(personRepository::save);
    }
}
