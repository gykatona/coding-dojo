package com.dojo.codingdojo.repository;

import com.dojo.codingdojo.pojo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String getStorePersonToDatabaseQuery() {
        return "INSERT INTO people (first_name, last_name, age) VALUES (:firstName, :lastName, :age) ON CONFLICT DO NOTHING";
    }

    private String getFetchPersonSql() {
        return "SELECT last_name AS lastName, first_name AS firstName, age FROM people_to_csv";
    }

    public List<Person> fetchPerson() {
        return jdbcTemplate.query(
                getFetchPersonSql(),
                new BeanPropertyRowMapper<>(Person.class)
        );
    }

    public void save(Person person) {
        jdbcTemplate.update(
                getStorePersonToDatabaseQuery(),
                new BeanPropertySqlParameterSource(person));
    }
}
