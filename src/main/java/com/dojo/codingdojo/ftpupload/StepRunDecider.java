package com.dojo.codingdojo.ftpupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

@Slf4j
public class StepRunDecider implements JobExecutionDecider {

    private int generateRandomNumber() {
        return new Random().nextInt(10) + 1;

    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (generateRandomNumber() >= 5 ) {
            log.info("Run exit status: RUN");
            return new FlowExecutionStatus("RUN");
        } else {
            log.info("Run exit status: NOT RUN");
            return new FlowExecutionStatus("NOT RUN");
        }
    }
}
