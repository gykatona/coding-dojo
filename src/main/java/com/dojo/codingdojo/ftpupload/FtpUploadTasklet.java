package com.dojo.codingdojo.ftpupload;

import com.dojo.codingdojo.ftp.FtpActions;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FtpUploadTasklet implements Tasklet {
    private final FtpActions ftpActions;

    @Value("${remote.file}")
    private String fileName;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        ftpActions.upload(new FileSystemResource(fileName));
        return RepeatStatus.FINISHED;
    }
}
