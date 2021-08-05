package com.dojo.codingdojo.personfilestep.reader;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.repository.PersonRepository;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonFetcher implements ItemReader<Person> {

    private final PersonRepository personRepository;
    private int nextPersonIndex;
    private List<Person> personList;

    public PersonFetcher(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @BeforeStep
    public void init() {
        personList = personRepository.fetchPerson();
    }

    @Override
    public Person read() {
        Person nextPerson = null;

        if (nextPersonIndex < personList.size()) {
            nextPerson = personList.get(nextPersonIndex);
            nextPersonIndex++;
        } else {
            nextPersonIndex = 0;
        }

        return nextPerson;
    }
}
