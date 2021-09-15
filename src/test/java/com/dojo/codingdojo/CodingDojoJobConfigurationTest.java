package com.dojo.codingdojo;

import com.dojo.codingdojo.repository.PrimaryRepository;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CodingDojoJobConfiguration.class)
@RequiredArgsConstructor
public class CodingDojoJobConfigurationTest {

    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final PrimaryRepository personRepository;



}