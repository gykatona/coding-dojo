package com.dojo.codingdojo.personfilestep.reader;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.repository.PrimaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonFetcher implements ItemReader<Person> {

    private final PrimaryRepository personRepository;
    private int nextPersonIndex;
    private List<Person> personList;

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
