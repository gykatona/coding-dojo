package com.dojo.codingdojo;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { JobConfiguration.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CodingDojoApplicationTests {

	private final String EXPECTED_OUTPUT = "";
	private final String TEST_OUTPUT = "";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@After
	public void cleanUp() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	private JobParameters defaultJobParameters() {
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("csv.fileName", "feladat_1.csv");
		paramsBuilder.addString("csv.fields", "last_name,first_name,age");
		return paramsBuilder.toJobParameters();
	}

	@Test
	void givenReferenceOutputWhenJobExecutedThenSuccess() throws Exception {

		FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
		FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

		JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
		JobInstance actualJobInstance = jobExecution.getJobInstance();
		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

		assertThat(actualJobInstance.getJobName(), is("codingDojo"));
		assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
		AssertFile.assertFileEquals(expectedResult, actualResult);
	}

}
