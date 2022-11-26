package com.example.demo.listener;

import com.example.demo.writer.dto.WriteDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JobListener extends JobExecutionListenerSupport {

    private static final String SQL_SELECT_CREDENTIALS = "SELECT * FROM DATA_WRITER_TABLE";

    @Qualifier("write")
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(jobExecution.getStatus().name());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Migration is Done!!!");
            jdbcTemplate.query(SQL_SELECT_CREDENTIALS,
                    (rs, row) -> new WriteDTO(
                            Long.valueOf(rs.getString(1)),
                            rs.getString(2))
            ).forEach(credentials -> log.info("New record -> " + credentials));
        }
    }
}