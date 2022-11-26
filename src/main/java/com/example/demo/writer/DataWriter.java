package com.example.demo.writer;

import com.example.demo.writer.dto.WriteDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataWriter {

    private static final String QUERY_INSERT = "INSERT INTO DATA_WRITER_TABLE(id, name) " +
            "VALUES (:id, :name)";

    @Bean
    public ItemWriter<WriteDTO> databaseItemWriter(DataSource dataSource,
                                                   NamedParameterJdbcTemplate jdbcTemplate) {
        JdbcBatchItemWriter<WriteDTO> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(dataSource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);

        databaseItemWriter.setSql(QUERY_INSERT);
        ItemSqlParameterSourceProvider<WriteDTO> paramProvider =
                new BeanPropertyItemSqlParameterSourceProvider<>();
        databaseItemWriter.setItemSqlParameterSourceProvider(paramProvider);
        return databaseItemWriter;
    }
}
