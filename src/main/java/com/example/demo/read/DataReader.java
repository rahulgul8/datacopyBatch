package com.example.demo.read;

import com.example.demo.read.dto.ReadDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataReader {

    @Autowired
    private DataSource readDataSource;

    @Bean
    public ItemReader<ReadDTO> getPaginationReader() {
        final JdbcPagingItemReader<ReadDTO> reader = new JdbcPagingItemReader<>();
        final DataRowMapper studentMapper = new DataRowMapper();
        reader.setDataSource(readDataSource);
        reader.setFetchSize(2);
        reader.setPageSize(4);
        reader.setRowMapper(studentMapper);
        reader.setQueryProvider(createQuery());
        reader.setParameterValues(getQueryParams());
        return reader;
    }

    private PagingQueryProvider createQuery() {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        final OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("ID as id, NAME as name");
        queryProvider.setFromClause(getFromClause());
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

    private String getFromClause() {
        return "DATA_READ_TABLE";
    }

    private Map<String, Object> getQueryParams() {
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("id", dto.getId());
//        parameters.put("name", dto.getName());
        return parameters;
    }
}
