package com.dojo.codingdojo.remoteload;


import com.dojo.codingdojo.remoteload.repository.RemoteLoadRepository;
import common.CustomExitStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class RemoteLoadTasklet implements Tasklet, StepExecutionListener {

    private final RemoteLoadRepository remoteLoadRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        remoteLoadRepository.loadDumpIntoTable();
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        int number = generateRandomNumber();
        log.info("Random number was {}.", number);
        if (number >= 5) {
            return CustomExitStatus.FTP;
        }

        return ExitStatus.COMPLETED;
    }

    private int generateRandomNumber() {
        return new Random().nextInt(10) + 1;
    }
}
