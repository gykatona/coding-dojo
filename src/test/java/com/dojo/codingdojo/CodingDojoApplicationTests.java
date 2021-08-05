package com.dojo.codingdojo;



//@RunWith(SpringRunner.class)
//@SpringBatchTest
//@EnableAutoConfiguration
//@ContextConfiguration(classes = { CodingDojoJobConfiguration.class })
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//		DirtiesContextTestExecutionListener.class})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CodingDojoApplicationTests {

//	private final String EXPECTED_OUTPUT = "";
//	private final String TEST_OUTPUT = "";
//
//	@Autowired
//	private JobLauncherTestUtils jobLauncherTestUtils;
//
//	@Autowired
//	private JobRepositoryTestUtils jobRepositoryTestUtils;
//
//	@After
//	public void cleanUp() {
//		jobRepositoryTestUtils.removeJobExecutions();
//	}
//
//	private JobParameters defaultJobParameters() {
//		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
//		paramsBuilder.addString("csv.fileName", "feladat_1.csv");
//		paramsBuilder.addString("csv.fields", "last_name,first_name,age");
//		return paramsBuilder.toJobParameters();
//	}
//
//	@Test
//	void givenReferenceOutputWhenJobExecutedThenSuccess() throws Exception {
//
//		FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
//		FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);
//
//		JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
//		JobInstance actualJobInstance = jobExecution.getJobInstance();
//		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
//
//		assertThat(actualJobInstance.getJobName(), is("codingDojo"));
//		assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
//		AssertFile.assertFileEquals(expectedResult, actualResult);
//	}

}
