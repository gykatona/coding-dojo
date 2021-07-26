package com.dojo.codingdojo;

import com.dojo.codingdojo.pojo.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        person.setFirstName(person.getFirstName().concat("test"));
        return person;
    }
}
