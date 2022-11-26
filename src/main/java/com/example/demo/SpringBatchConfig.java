package com.example.demo;

import com.example.demo.listener.*;
import com.example.demo.read.dto.ReadDTO;
import com.example.demo.writer.dto.WriteDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobListener listener;

    @Bean
    protected Step step1(ItemReader<ReadDTO> reader,
                         ItemProcessor<ReadDTO, WriteDTO> processor,
                         ItemWriter<WriteDTO> writer) {
        return steps.get("step1").<ReadDTO, WriteDTO>chunk(2)
                .reader(reader).processor(processor).writer(writer)
                .listener(new DataChunkListener())
                .listener(new DataItemReadListener())
                .listener(new DataWriteListener())
                .listener(new StepListener()).build();
    }

    @Bean
    public Job job(Step step1) {
        return jobs.get("firstBatchJob1").listener(listener).incrementer(new RunIdIncrementer()).start(step1).build();
    }
}