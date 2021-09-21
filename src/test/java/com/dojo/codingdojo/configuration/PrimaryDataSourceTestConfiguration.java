package com.dojo.codingdojo.configuration;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.repository.PrimaryRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class PrimaryDataSourceTestConfiguration {

    @MockBean
    private PrimaryRepository primaryRepository;

//    @Bean
//    public List<Person> fetchTestPersons() throws SQLException {
//        return Mockito.when(primaryRepository.fetchPerson()).thenReturn(Arrays.asList(
//                new Person("Mikorka", "Kalman", 20),
//                new Person("Trab", "Antal", 18)
//        ));
//        //        return new MppBlockedKeywordBatchProvider(new AmazonMarketplace(""), mppBlockedKeywordRepository);
//    }

}
