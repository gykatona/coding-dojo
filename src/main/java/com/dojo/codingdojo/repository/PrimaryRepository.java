package com.dojo.codingdojo.repository;

import com.dojo.codingdojo.pojo.Listing;
import com.dojo.codingdojo.pojo.Marketplace;
import com.dojo.codingdojo.pojo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PrimaryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String getStorePersonToDatabaseQuery() {
        return "INSERT INTO people (first_name, last_name, age) VALUES (:firstName, :lastName, :age) ON CONFLICT DO NOTHING";
    }

    private String getFetchPersonSql() {
        return "SELECT last_name AS lastName, first_name AS firstName, age FROM people_to_csv";
    }

    private String getStoreMarketplaceToDatabaseQuery() {
        return "INSERT INTO marketplace (marketplace) VALUES (:marketplaceName) ON CONFLICT DO NOTHING";
    }

    private String getStoreListingToDatabaseQuery() {
        return "INSERT INTO listing_status (status) VALUES (:statusName) ON CONFLICT DO NOTHING";
    }

    public List<Person> fetchPerson() {
        return jdbcTemplate.query(
                getFetchPersonSql(),
                new BeanPropertyRowMapper<>(Person.class)
        );
    }

    public void savePerson(Person person) {
        jdbcTemplate.update(
                getStorePersonToDatabaseQuery(),
                new BeanPropertySqlParameterSource(person));
    }

    public void saveMarketplace(Marketplace marketplace) {
        jdbcTemplate.update(
                getStoreMarketplaceToDatabaseQuery(),
                new BeanPropertySqlParameterSource(marketplace));
    }

    public void saveListing(Listing listing) {
        jdbcTemplate.update(
                getStoreListingToDatabaseQuery(),
                new BeanPropertySqlParameterSource(listing));
    }
}
