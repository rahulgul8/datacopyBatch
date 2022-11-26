package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.jsr.StepListenerAdapter;

@Slf4j
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("beforeStep().");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Called afterStep().");
        return ExitStatus.COMPLETED;
    }
}
