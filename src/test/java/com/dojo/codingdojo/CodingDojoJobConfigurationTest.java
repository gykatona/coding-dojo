package com.dojo.codingdojo;

import com.dojo.codingdojo.pojo.Person;
import com.dojo.codingdojo.pojo.Source;
import com.dojo.codingdojo.repository.PrimaryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBatchTest
@ContextConfiguration(classes = {TestApplication.class, CodingDojoJobConfiguration.class}, initializers = ConfigFileApplicationContextInitializer.class)
@DirtiesContext
public class CodingDojoJobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    private PrimaryRepository primaryRepository;

    @MockBean
    private JdbcPagingItemReader<Source> remoteDumpReader;

    @Test
    public void testRemoteDumpStep() throws Exception {
        Mockito.when(remoteDumpReader.read())
                .thenReturn(createSource());

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("remoteDumpStep");

        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        String EXPECTED_FILE = "src/test/resources/test_remote_persons.csv";
        String OUTPUT_FILE = "output/remote_persons.csv";

        AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE),
                new FileSystemResource(OUTPUT_FILE));
    }

    @Test
    public void testPersonFileStep() throws Exception {
        Mockito.when(primaryRepository.fetchPerson())
                .thenReturn(List.of(createPersonList()));

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("personFileWriterStep");

        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        String EXPECTED_FILE = "src/test/resources/test_feladat_1.csv";
        String OUTPUT_FILE = "src/main/resources/feladat_1.csv";

        AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE),
                new FileSystemResource(OUTPUT_FILE));
    }

    private Person createPersonList() {
        return new Person("Mikorka", "Kalman", 20);
    }

    private Source createSource() {
        return new Source(1, "Elek", "Test", 25);
    }

}