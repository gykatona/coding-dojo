package com.dojo.codingdojo.remoteload;

import com.dojo.codingdojo.remoteload.repository.RemoteLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoteLoadTasklet implements Tasklet {

    private final RemoteLoadRepository remoteLoadRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        remoteLoadRepository.loadDumpIntoTable();
        return RepeatStatus.FINISHED;
    }
}
